package win.techflowing.service.manager.provider

import win.techflowing.service.manager.IService

/**
 * 一次性 Service 提供，每次都创建新的实例
 *
 * @author techflowing@gmail.com
 * @since 2022/6/23 11:53 下午
 */
abstract class OnceServiceProvider<IMPL : IService> : IServiceProvider<IMPL> {

    override fun provideService(): IMPL {
        return create()
    }

    abstract fun create(): IMPL
}