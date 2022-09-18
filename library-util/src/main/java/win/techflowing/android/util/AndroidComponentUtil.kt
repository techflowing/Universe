package win.techflowing.android.util

import android.content.Context
import android.content.Intent

/**
 * Android 四大组件相关的工具类
 *
 * @author techflowing@gmail.com
 * @since 2022/9/18 23:04
 */
object AndroidComponentUtil {

    /**
     * startService 增加 try catch
     *
     * @param context
     * @param intent
     */
    fun safeStartService(context: Context, intent: Intent) {
        try {
            context.startService(intent)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }
}