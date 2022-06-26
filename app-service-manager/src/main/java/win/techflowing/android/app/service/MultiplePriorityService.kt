package win.techflowing.android.app.service

import win.techflowing.service.manager.IService

/**
 * 测试不同优先级
 *
 * @author techflowing@gmail.com
 * @since 2022/6/26 10:25 下午
 */
interface MultiplePriorityService :IService{
    fun sayHello()
}