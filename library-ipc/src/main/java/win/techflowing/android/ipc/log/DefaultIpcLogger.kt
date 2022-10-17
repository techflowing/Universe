package win.techflowing.android.ipc.log

import android.util.Log

/**
 * 使用 Java Log 默认实现的日志输出
 *
 * @author techflowing@gmail.com
 * @since 2022/9/25 18:15
 */
class DefaultIpcLogger : ILogger {

    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    override fun i(tag: String, msg: String) {
        Log.i(tag, msg)
    }

    override fun w(tag: String, msg: String) {
        Log.w(tag, msg)
    }

    override fun e(tag: String, msg: String) {
        Log.e(tag, msg)
    }

    override fun e(tag: String, msg: String, tr: Throwable) {
        Log.e(tag, msg, tr)
    }
}