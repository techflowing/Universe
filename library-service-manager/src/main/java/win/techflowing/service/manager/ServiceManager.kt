package win.techflowing.service.manager

import win.techflowing.service.manager.provider.IServiceProvider
import java.util.concurrent.ConcurrentHashMap

/**
 * Service 管理框架核心类
 *
 * @author techflowing@gmail.com
 * @since 2022/5/26 11:56 下午
 */
class ServiceManager private constructor() {

    /** 缓存 Service 和 ServiceProvider 的映射关系 */
    private val serviceProviderMap = ConcurrentHashMap<String, IServiceProvider<*>>()

    fun <T : IService> service(clazz: Class<T>): T? {
        // 优先判断缓存
        var provider = serviceProviderMap[clazz.name]
        if (provider != null) {
            return provider.provideService() as T
        }
        synchronized(this) {
            // 二次判断
            provider = serviceProviderMap[clazz.name]
            if (provider != null) {
                return provider!!.provideService() as T
            }
            // 反射尝试获取 ServiceProvider 类
            val serviceProvider = reflectServiceProvider(clazz)
            if (serviceProvider != null) {
                serviceProviderMap[clazz.name] = serviceProvider
                return serviceProvider.provideService() as T
            }
        }
        return null
    }

    /**
     * 反射获取 ServiceProvider 类
     *
     * @param clazz Service 类
     * @return ServiceProvider 类
     */
    private fun <T : IService> reflectServiceProvider(clazz: Class<T>): IServiceProvider<*>? {
        val name = clazz.name + PROVIDER_SUFFIX
        return try {
            val providerClass = Class.forName(name)
            providerClass.newInstance() as IServiceProvider<*>?
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    companion object {

        /** 生成的Service 构造类名称后缀 */
        const val PROVIDER_SUFFIX = "_ServiceManagerProvider"

        /** 单例实现 */
        private val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ServiceManager()
        }

        @JvmStatic
        fun <T : IService> of(clazz: Class<T>): T? {
            return instance.service(clazz)
        }
    }
}