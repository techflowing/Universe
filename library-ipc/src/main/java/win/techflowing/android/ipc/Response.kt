package win.techflowing.android.ipc

import android.os.Parcel
import android.os.Parcelable

/**
 * 远程调用执行结果 Response
 *
 * @author techflowing@gmail.com
 * @since 2022/9/25 15:37
 */
class Response() : SuperParcelable {

    constructor(parcel: Parcel) : this() {
        readFromParcel(parcel)
    }

    override fun readFromParcel(parcel: Parcel) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Response> {
        override fun createFromParcel(parcel: Parcel): Response {
            return Response(parcel)
        }

        override fun newArray(size: Int): Array<Response?> {
            return arrayOfNulls(size)
        }
    }
}