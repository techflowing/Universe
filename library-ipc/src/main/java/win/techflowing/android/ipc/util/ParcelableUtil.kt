package win.techflowing.android.ipc.util

import android.os.Parcel
import android.os.Parcelable
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

    inline fun <reified T : Parcelable> readParcelableArray(loader: ClassLoader, parcel: Parcel): Array<T>? {
        val size = parcel.readInt()
        if (size < 0) {
            return null
        }
        val result = mutableListOf<T>()
        for (index in 0 until size) {
            result.add(index, parcel.readParcelable(loader)!!)
        }
        return result.toTypedArray()
    }

    fun <T : Parcelable> writeParcelableArrayToParcel(parcel: Parcel, values: Array<T>?, flags: Int) {
        if (values != null) {
            val size = values.size
            parcel.writeInt(size)
            for (index in 0 until size) {
                values[index].writeToParcel(parcel, flags)
            }
        } else {
            parcel.writeInt(-1)
        }
    }

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