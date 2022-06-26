package win.techflowing.android.app.service

import android.util.Log
import win.techflowing.service.manager.annotation.Priority
import win.techflowing.service.manager.annotation.ServiceImpl

/**
 * 默认先级实现
 *
 * @author techflowing@gmail.com
 * @since 2022/6/26 10:25 下午
 */
@ServiceImpl
class MultiplePriorityServiceDefaultImpl : MultiplePriorityService {

    companion object {
        const val TAG = "MultiplePriorityServiceDefaultImpl"
    }

    override fun sayHello() {
        Log.e(TAG, "sayHello，MultiplePriorityServiceDefaultImpl")
    }
}