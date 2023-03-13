package win.techflowing.android.ipc.call

import android.os.Parcel
import android.os.Parcelable

/**
 * 远程调用执行结果 Response
 *
 * @author techflowing@gmail.com
 * @since 2022/9/25 15:37
 */
class Response : Parcelable {

    private var statusCode: Int = 0
    private var statusMessage: String? = null
    private var result: Any? = null

    constructor(statusCode: Int, statusMessage: String) : this(statusCode, statusMessage, null)

    constructor(statusCode: Int, statusMessage: String, result: Any?) {
        this.statusCode = statusCode
        this.statusMessage = statusMessage
        this.result = result
    }

    private constructor(parcel: Parcel) {
        statusCode = parcel.readInt()
        statusMessage = parcel.readString()
        result = parcel.readValue(javaClass.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(statusCode)
        parcel.writeString(statusMessage)
        parcel.writeValue(result)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun getStatusCode(): Int {
        return statusCode
    }

    fun getStatusMessage(): String? {
        return statusMessage
    }

    fun getResult(): Any? {
        return result
    }

    override fun toString(): String {
        return "Response{ mStatusCode=$statusCode, mStatusMessage='$statusMessage', mResult=$result }"
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