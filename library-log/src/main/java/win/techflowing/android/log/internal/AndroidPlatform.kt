package win.techflowing.android.log.internal

import android.util.Log

/**
 * Android 平台实现
 *
 * @author techflowing@gmail.com
 * @since 2022/5/19 1:30 上午
 */
class AndroidPlatform : Platform() {

    override fun warn(message: String) {
        Log.w(TAG, message)
    }

    override fun error(message: String) {
        Log.e(TAG, message)
    }
}