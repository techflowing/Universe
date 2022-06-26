package win.techflowing.android.app.service

import android.util.Log
import win.techflowing.service.manager.annotation.Scope
import win.techflowing.service.manager.annotation.ServiceImpl

/**
 * 一次性Service 测试
 *
 * @author techflowing@gmail.com
 * @since 2022/6/26 10:05 下午
 */
@ServiceImpl(scope = Scope.ONCE)
class OnceServiceImpl : OnceService {

    companion object {
        const val TAG = "OnceServiceImpl"
    }

    override fun sayHello() {
        Log.e(TAG, "sayHello，当前对象值：" + toString())
    }
}