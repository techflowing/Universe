package win.techflowing.android.ipc.util

import android.os.Parcel
import android.os.Parcelable

/**
 * Parcelable 相关的工具类
 *
 * @author techflowing@gmail.com
 * @since 2022/11/6 18:24
 */
object ParcelableUtil {

    inline fun <reified T : Parcelable> readParcelableArray(loader: ClassLoader, parcel: Parcel): Array<T>? {
        val size = parcel.readInt()
        if (size < 0) {
            return null
        }
        val result = mutableListOf<T>()
        for (index in 0 until size) {
            result[index] = parcel.readParcelable(loader)!!
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
}