package win.techflowing.android.ipc.call

import android.os.Parcel
import android.os.Parcelable
import win.techflowing.android.ipc.parameter.wrapper.BaseParameterWrapper
import win.techflowing.android.ipc.util.ParcelableUtil

/**
 * 远程 Service 调用请求，封装调用 Service 的类信息，方法信息，参数信息等
 *
 * @author techflowing@gmail.com
 * @since 2022/9/25 17:25
 */
class Request : Parcelable {

    private var targetClass: String? = null
    private var methodName: String? = null
    private var argsWrapper: Array<BaseParameterWrapper>? = null
    private var oneway: Boolean = false

    constructor(
        targetClass: String?,
        methodName: String?,
        argsWrapper: Array<BaseParameterWrapper>?,
        oneway: Boolean
    ) {
        this.targetClass = targetClass
        this.methodName = methodName
        this.argsWrapper = argsWrapper
        this.oneway = oneway
    }

    private constructor(parcel: Parcel) {
        this.targetClass = parcel.readString()
        this.methodName = parcel.readString()
        this.argsWrapper = ParcelableUtil.readParcelableArray(javaClass.classLoader!!, parcel)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(targetClass)
        parcel.writeString(methodName)
        if (flags == Parcelable.PARCELABLE_WRITE_RETURN_VALUE) {
            ParcelableUtil.writeParcelableArrayToParcel(parcel, argsWrapper, flags)
        } else {
            parcel.writeParcelableArray(argsWrapper, flags)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    fun getTargetClass(): String? {
        return targetClass
    }

    fun getMethodName(): String? {
        return methodName
    }

    fun getArgsWrapper(): Array<BaseParameterWrapper>? {
        return argsWrapper
    }

    fun isOneway(): Boolean {
        return oneway
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