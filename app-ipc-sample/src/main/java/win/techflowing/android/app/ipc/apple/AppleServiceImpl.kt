package win.techflowing.android.app.ipc.apple

import android.os.Parcelable
import win.techflowing.android.log.XLog

/**
 * Apple Service 实现
 *
 * @author techflowing@gmail.com
 * @since 2022/10/16 22:59
 */
class AppleServiceImpl : AppleService {

    companion object {
        const val TAG = "AppleServiceImpl"
    }

    override fun getAppleName(): String {
        return "苹果"
    }
}