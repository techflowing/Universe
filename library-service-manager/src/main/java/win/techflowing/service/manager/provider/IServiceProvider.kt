package win.techflowing.service.manager.provider

import win.techflowing.service.manager.IService

/**
 * 获取 Service 实例
 *
 * @author techflowing@gmail.com
 * @since 2022/6/9 11:50 下午
 */
interface IServiceProvider<IMPL : IService> {

    /** 创建 Service 实现类实例 */
    fun provideService(): IMPL
}