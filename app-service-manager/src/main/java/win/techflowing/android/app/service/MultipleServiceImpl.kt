package win.techflowing.android.app.service

import android.util.Log
import win.techflowing.service.manager.annotation.ServiceImpl

/**
 * 测试多实现 Service
 *
 * @author techflowing@gmail.com
 * @since 2022/6/26 10:28 下午
 */
@ServiceImpl
class MultipleServiceImpl : MultipleServiceOne, MultipleServiceTwo {

    companion object {
        const val TAG = "MultipleServiceImpl"
    }

    override fun sayHelloOne() {
        Log.e(TAG, "sayHelloOne: " + toString())
    }

    override fun sayHelloTwo() {
        Log.e(TAG, "sayHelloTwo: " + toString())
    }
}