package win.techflowing.android.app.service

import win.techflowing.service.manager.IService

/**
 * Service 多实现测试
 *
 * @author techflowing@gmail.com
 * @since 2022/6/26 10:27 下午
 */
interface MultipleServiceTwo : IService {
    fun sayHelloTwo()
}