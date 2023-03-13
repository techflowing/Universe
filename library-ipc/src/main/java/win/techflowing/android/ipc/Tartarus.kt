package win.techflowing.android.ipc

import android.app.Application
import android.content.Context
import android.util.Log
import win.techflowing.android.ipc.call.adapter.CallAdapterFactory
import win.techflowing.android.ipc.core.IRemoteService
import win.techflowing.android.ipc.core.ServiceManager
import win.techflowing.android.ipc.log.DefaultIpcLogger
import win.techflowing.android.ipc.log.ILogger

/**
 * 阿布索留特·塔尔塔洛斯，奥特银河格斗反派角色，擅长时空穿梭
 *
 * @author techflowing@gmail.com
 * @since 2022/8/26 12:23 上午
 */
object Tartarus {

    /** 日志 TAG */
    private const val TAG = "Tartarus"

    /** 初始化配置 */
    private lateinit var configuration: IpcConfiguration

    /** 是否已经执行过初始化 */
    private var initialized: Boolean = false

    /** 默认的日志打印器*/
    private val defaultLogger = DefaultIpcLogger()

    /**
     * 初始化方法
     *
     * @param configuration 初始化配置
     */
    fun init(configuration: IpcConfiguration) {
        if (initialized) {
            Log.w(TAG, "Tartarus is already initialized, do not initialize again")
            return
        }
        initialized = true
        this.configuration = configuration
    }

    /**
     * 获取Context
     *
     * @return Context
     */
    fun getContext(): Context {
        return configuration.getContext()
    }

    /**
     * 获取 Application
     *
     * @return Application
     */
    fun getApplication(): Application {
        return configuration.getApplication()
    }

    /**
     * 获取日志打印器
     *
     * @return 日志打印器
     */
    fun getLogger(): ILogger {
        return configuration.getLogger() ?: defaultLogger
    }

    /**
     * 获取 CallAdapterFactory 集合
     *
     * @return CallAdapterFactory 集合
     */
    fun getAdapterFactory(): List<CallAdapterFactory> {
        return configuration.getAdapterFactory()
    }

    /**
     * 注册 Service
     *
     * @param SERVICE Service 类型
     * @param IMPL Service 实现类 类型
     * @param service Service
     * @param serviceImpl Service 实现类
     */
    fun <SERVICE : IRemoteService, IMPL : SERVICE> registerRemoteService(service: Class<SERVICE>, serviceImpl: IMPL) {
        ServiceManager.get().registerRemoteService(service, serviceImpl)
    }

    /**
     * 反注册 Service
     *
     * @param SERVICE Service 类型
     * @param service Service
     */
    fun <SERVICE : IRemoteService> unregisterRemoteService(service: Class<SERVICE>) {
        ServiceManager.get().unregisterRemoteService(service)
    }

    /**
     * 获取 Service
     *
     * @param SERVICE Service 类型
     * @param service Service
     */
    fun <SERVICE : IRemoteService> getRemoteService(service: Class<SERVICE>): SERVICE? {
        return ServiceManager.get().getRemoteService(service)
    }
}