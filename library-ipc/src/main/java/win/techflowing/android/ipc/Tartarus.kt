package win.techflowing.android.ipc

import android.app.Application
import android.content.Context
import win.techflowing.android.ipc.core.ServiceManager

/**
 * 阿布索留特·塔尔塔洛斯，奥特银河格斗反派角色，擅长时空穿梭
 *
 * @author techflowing@gmail.com
 * @since 2022/8/26 12:23 上午
 */
object Tartarus {

    /** 初始化设置的 Application */
    private lateinit var application: Application

    /**
     * 初始化方法
     *
     * @param application 应用 Application
     */
    fun init(application: Application) {
        this.application = application
    }

    /**
     * 获取Context
     *
     * @return Context
     */
    fun getContext(): Context {
        return application
    }

    /**
     * 获取 Application
     *
     * @return Application
     */
    fun getApplication(): Application {
        return application
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

    fun <SERVICE : IRemoteService> getRemoteService(service: Class<SERVICE>): SERVICE? {
        return ServiceManager.get().getRemoteService(service)
    }
}