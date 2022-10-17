package win.techflowing.android.ipc.core

import android.os.IBinder
import android.os.Process
import android.os.RemoteException
import win.techflowing.android.ipc.*
import win.techflowing.android.ipc.log.Logger
import win.techflowing.android.ipc.test.RemoteServiceImpl
import win.techflowing.android.util.ProcessUtil
import java.util.concurrent.ConcurrentHashMap

/**
 * Service 管理，运行于每一个进程内
 *
 * @author techflowing@gmail.com
 * @since 2022/9/17 17:07
 */
class ServiceManager private constructor() : IServiceManager.Stub() {

    /** 锁 */
    private val lock = Object()

    /** 本进程内的 Service 缓存 */
    private val localServiceMap: MutableMap<Class<*>, Any> = ConcurrentHashMap()

    /** 其它进程的 Service 缓存 */
    private val remoteServiceMap: MutableMap<Class<*>, IBinder> = ConcurrentHashMap()

    /** ServiceDispatcher 进程位于本进程的 Binder Proxy 代理对象 */
    private var serviceDispatcherProxy: IServiceDispatcher? = null

    fun <SERVICE : IRemoteService, IMPL : SERVICE> registerRemoteService(service: Class<SERVICE>, serviceImpl: IMPL) {
        // 先加下本地缓存
        localServiceMap[service] = serviceImpl
        initServiceDispatcherProxyLocked()
        service.canonicalName?.also {
            if (serviceDispatcherProxy != null) {
                registerRemoteServiceByServiceDispatcherProxy(it)
            } else {
                registerRemoteServiceByStartService(it)
            }
        }
    }

    fun <SERVICE : IRemoteService, IMPL : SERVICE> getRemoteService(service: Class<SERVICE>): IMPL {
        return RemoteServiceImpl() as IMPL
    }

    override fun registerServiceDispatcher(serviceDispatcherBinder: IBinder?) {
        serviceDispatcherBinder?.also {
            it.linkToDeath({
                Logger.i(TAG, "remote ServiceDispatcher binderDied")
                serviceDispatcherProxy = null
            }, 0)
            serviceDispatcherProxy = IServiceDispatcher.Stub.asInterface(it)
            synchronized(lock) {
                lock.notifyAll()
            }
        }
    }

    override fun unregisterService(serviceCanonicalName: String?) {
        TODO("Not yet implemented")
    }

    /**
     * 通过 start DispatcherProcessService 的方式注册 RemoteService
     *
     * @param serviceName 服务类名称
     */
    private fun registerRemoteServiceByStartService(serviceName: String) {
        DispatcherProcessService.registerRemoteService(
            Tartarus.getContext(),
            serviceName,
            this.asBinder(),
            Transporter.getInstance().asBinder()
        )
    }

    /**
     * 通过 ServiceDispatcherProxy 注册服务
     *
     * @param serviceName 服务类名称
     */
    private fun registerRemoteServiceByServiceDispatcherProxy(serviceName: String) {
        if (serviceDispatcherProxy == null) {
            return
        }
        val processName = ProcessUtil.getCurrentProcessName(Tartarus.getContext())
        try {
            serviceDispatcherProxy!!.registerService(
                serviceName,
                Process.myPid(),
                processName,
                Transporter.getInstance().asBinder()
            )
        } catch (e: RemoteException) {
            Logger.e(TAG, "registerRemoteServiceByServiceDispatcherProxy exception", e)
        }
    }

    /** 获取 ServiceDispatcher Proxy 代理对象 */
    private fun initServiceDispatcherProxyLocked() {
        if (serviceDispatcherProxy != null) {
            return
        }
        synchronized(this) {
            if (serviceDispatcherProxy != null) {
                return
            }
            val context = Tartarus.getContext()
            val uri = DispatcherProcessProvider.getProviderUri(context)
            context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                BinderWrapperCursor.stripBinder(cursor)?.also { binder ->
                    serviceDispatcherProxy = IServiceDispatcher.Stub.asInterface(binder)
                    registerServiceManagerToServiceDispatcher()
                }
            }
            if (serviceDispatcherProxy != null) {
                return
            }
            // 通过 startService 的方式通知 ServiceDispatcher 注册 Binder 对象到本进程
            DispatcherProcessService.notifyRegisterServiceDispatcher(context, this.asBinder());
            synchronized(lock) {
                if (serviceDispatcherProxy != null) {
                    return
                }
                try {
                    Logger.i(TAG, "开启等待")
                    lock.wait(600)
                } catch (e: InterruptedException) {
                    Logger.e(TAG, "等待被打断", e)
                }
            }
        }
    }

    /**
     * 通过 Binder 跨进程向 ServiceDispatcher 进程注册本进程的 ServiceManager Binder对象
     */
    private fun registerServiceManagerToServiceDispatcher() {
        try {
            serviceDispatcherProxy?.registerServiceManager(Process.myPid(), this.asBinder())
        } catch (e: RemoteException) {
            Logger.e(TAG, "向远程 ServiceDispatcher 注册 ServiceManager 异常", e)
        }
    }

    companion object {
        const val TAG = "ServiceManager"
        private val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ServiceManager()
        }

        fun get(): ServiceManager {
            return instance
        }
    }
}