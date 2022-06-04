package win.techflowing.android.app.service

import win.techflowing.android.log.XLog
import win.techflowing.service.annotation.ServiceImpl

/**
 * Service 实现
 *
 * @author techflowing@gmail.com
 * @since 2022/6/4 10:26 下午
 */
@ServiceImpl
class ServiceOneImpl : ServiceOne {

    companion object {
        const val TAG = "ServiceOneImpl"
    }

    override fun sayHello() {
        XLog.e(TAG, "hello，$TAG")
    }
}