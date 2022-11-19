package win.techflowing.android.ipc.util

import android.os.Parcel
import win.techflowing.android.ipc.log.Logger
import java.lang.reflect.Method

/**
 * Parcelable 相关的工具类
 *
 * @author techflowing@gmail.com
 * @since 2022/11/6 18:24
 */
object ParcelableUtil {

    const val TAG = "ParcelableUtil"

    fun getMethodReadFromParcel(cls: Class<*>): Method? {
        var method: Method? = null
        try {
            method = cls.getMethod("readFromParcel", Parcel::class.java)
        } catch (e: NoSuchMethodException) {
            Logger.e(TAG, "No public readFromParcel() method in class:" + cls.name)
        } catch (e: SecurityException) {
            Logger.e(
                TAG, "SecurityException when get readFromParcel() method in class:" + cls.name
            )
        }
        return method
    }
}