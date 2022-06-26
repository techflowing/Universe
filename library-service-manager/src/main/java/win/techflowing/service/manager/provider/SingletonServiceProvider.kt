package win.techflowing.service.manager.provider

import win.techflowing.service.manager.IService

/**
 * 单例型 Service 创建
 *
 * @author techflowing@gmail.com
 * @since 2022/6/23 11:55 下午
 */
abstract class SingletonServiceProvider<IMPL : IService> : IServiceProvider<IMPL> {

    private var service: IMPL? = null
        get() {
            if (field == null) {
                field = create()
            }
            return field
        }

    override fun provideService(): IMPL {
        return service!!
    }

    abstract fun create(): IMPL
}