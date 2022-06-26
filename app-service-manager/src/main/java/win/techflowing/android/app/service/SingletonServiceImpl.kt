package win.techflowing.android.app.service

import android.util.Log
import win.techflowing.service.manager.annotation.ServiceImpl

/**
 * 单例 Service 测试
 *
 * @author techflowing@gmail.com
 * @since 2022/6/26 10:05 下午
 */
@ServiceImpl
class SingletonServiceImpl : SingletonService {

    companion object {
        const val TAG = "SingletonServiceImpl"
    }

    override fun sayHello() {
        Log.e(TAG, "sayHello，当前对象值：" + toString())
    }
}