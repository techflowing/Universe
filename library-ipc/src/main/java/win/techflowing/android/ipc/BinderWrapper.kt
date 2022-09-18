package win.techflowing.android.ipc

import android.os.IBinder
import android.os.Parcel
import android.os.Parcelable

/**
 * 使用 Parcelable 实现 Binder 跨进程传递
 *
 * @author techflowing@gmail.com
 * @since 2022/9/18 22:30
 */
class BinderWrapper(private val binder: IBinder) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readStrongBinder()) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStrongBinder(binder)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun getBinder(): IBinder {
        return binder
    }

    companion object CREATOR : Parcelable.Creator<BinderWrapper> {
        override fun createFromParcel(parcel: Parcel): BinderWrapper {
            return BinderWrapper(parcel)
        }

        override fun newArray(size: Int): Array<BinderWrapper?> {
            return arrayOfNulls(size)
        }
    }
}