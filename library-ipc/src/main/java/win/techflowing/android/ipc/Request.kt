package win.techflowing.android.ipc

import android.os.Parcel
import android.os.Parcelable

/**
 * 远程 Service 调用请求，封装调用 Service 的类信息，方法信息，参数信息等
 *
 * @author techflowing@gmail.com
 * @since 2022/9/25 17:25
 */
class Request() : SuperParcelable {

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun readFromParcel(parcel: Parcel) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Request> {
        override fun createFromParcel(parcel: Parcel): Request {
            return Request(parcel)
        }

        override fun newArray(size: Int): Array<Request?> {
            return arrayOfNulls(size)
        }
    }
}