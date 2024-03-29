package win.techflowing.android.ipc.core

import android.os.RemoteException
import win.techflowing.android.ipc.aidl.IServiceDispatcher
import win.techflowing.android.ipc.aidl.IServiceManager
import win.techflowing.android.ipc.aidl.ITransporter
import win.techflowing.android.ipc.log.Logger

/**
 * ServiceDispatcher 实现
 *
 * @author techflowing@gmail.com
 * @since 2022/9/26 22:51
 */
class ServiceDispatcher : IServiceDispatcher.Stub() {

    /** 维护所有进程的 ServiceManager Binder 代理对象 */
    private val serviceManagerMap = mutableMapOf<Int, IServiceManager>()

    /** 服务分发进程的 Map，维护全部进程的 Service 信息 */
    private val remoteServiceMap = mutableMapOf<String, RemoteServiceInfo>()

    @Synchronized
    override fun registerServiceManager(pid: Int, serviceManager: IServiceManager?) {
        serviceManager?.also {
            serviceManager.asBinder().linkToDeath({
                Logger.i(TAG, "remote serviceManagerBinder binderDied")
                serviceManagerMap.remove(pid)
            }, 0)
            serviceManagerMap[pid] = serviceManager
        }
    }

    @Synchronized
    override fun registerService(
        serviceCanonicalName: String,
        pid: Int,
        processName: String,
        transporter: ITransporter?
    ) {
        transporter?.also {
            transporter.asBinder().linkToDeath({
                Logger.i(TAG, "remoteService transporterBinder binderDied")
                remoteServiceMap.remove(serviceCanonicalName)
            }, 0)
            remoteServiceMap[serviceCanonicalName] =
                RemoteServiceInfo(serviceCanonicalName, pid, processName, transporter)
        }
    }

    @Synchronized
    override fun getServiceTransporterBinder(serviceCanonicalName: String?): ITransporter? {
        return remoteServiceMap[serviceCanonicalName]?.transporter
    }

    @Synchronized
    override fun unregisterService(serviceCanonicalName: String?) {
        remoteServiceMap.remove(serviceCanonicalName)
        // 通知所有进程移除缓存
        serviceManagerMap.forEach { entry ->
            try {
                entry.value.unregisterService(serviceCanonicalName)
            } catch (e: RemoteException) {
                e.printStackTrace()
                Logger.e(TAG, "unregisterService exception: ${e.message}")
            }
        }
    }

    /**
     * 远程服务的注册信息
     *
     * @property name 服务名称
     * @property pid 服务所属进程号
     * @property processName 服务所属进程名
     * @property transporter 服务转发代理 Binder
     */
    data class RemoteServiceInfo(
        val name: String,
        val pid: Int,
        val processName: String,
        val transporter: ITransporter
    )

    companion object {

        const val TAG = "ServiceDispatcher"

        private val ins by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ServiceDispatcher()
        }

        fun getInstance(): ServiceDispatcher {
            return ins
        }
    }
}