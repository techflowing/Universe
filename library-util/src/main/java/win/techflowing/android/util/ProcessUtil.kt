package win.techflowing.android.util

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Process
import android.text.TextUtils
import java.lang.reflect.Method


/**
 * 进程相关工具类
 *
 * @author techflowing@gmail.com
 * @since 2022/9/18 22:41
 */
object ProcessUtil {
    /** 当前进程名 */
    private var curProcessName: String? = null

    /** 获取当前进程名 */
    fun getCurrentProcessName(context: Context): String? {
        if (!TextUtils.isEmpty(curProcessName)) {
            return curProcessName
        }
        curProcessName = getCurrentProcessNameByApplication()
        if (!TextUtils.isEmpty(curProcessName)) {
            return curProcessName
        }
        curProcessName = getCurrentProcessNameByActivityThread()
        if (!TextUtils.isEmpty(curProcessName)) {
            return curProcessName
        }
        curProcessName = getCurrentProcessNameByActivityManager(context)
        return curProcessName
    }

    /** 通过Application的API获取当前进程名 */
    private fun getCurrentProcessNameByApplication(): String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Application.getProcessName()
        } else {
            null
        }
    }

    /** 通过反射ActivityThread获取进程名，避免了ipc */
    @SuppressLint("DiscouragedPrivateApi", "PrivateApi")
    private fun getCurrentProcessNameByActivityThread(): String? {
        var processName: String? = null
        try {
            val clz = Class.forName("android.app.ActivityThread", false, Application::class.java.classLoader)
            val declaredMethod: Method = clz.getDeclaredMethod("currentProcessName", *arrayOfNulls<Class<*>?>(0))
            declaredMethod.isAccessible = true
            val invoke: Any = declaredMethod.invoke(null, arrayOfNulls<Any>(0))
            if (invoke is String) {
                processName = invoke
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return processName
    }

    /** 通过ActivityManager 获取进程名，需要IPC通信 */
    private fun getCurrentProcessNameByActivityManager(context: Context): String? {
        val pid: Int = Process.myPid()
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningAppList = am.runningAppProcesses
        if (runningAppList != null) {
            for (processInfo in runningAppList) {
                if (processInfo.pid == pid) {
                    return processInfo.processName
                }
            }
        }
        return null
    }
}