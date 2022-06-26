package win.techflowing.android.app.service

import win.techflowing.service.manager.IService

/**
 * 一次性 Service 测试
 *
 * @author techflowing@gmail.com
 * @since 2022/6/26 10:04 下午
 */
interface OnceService : IService {

    fun sayHello()
}