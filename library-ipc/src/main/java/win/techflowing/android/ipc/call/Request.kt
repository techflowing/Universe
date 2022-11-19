package win.techflowing.android.ipc.call

import android.os.Parcel
import android.os.Parcelable
import win.techflowing.android.ipc.parameter.ISyncRemoteValue
import win.techflowing.android.ipc.parameter.wrapper.BaseParameterWrapper

/**
 * 远程 Service 调用请求，封装调用 Service 的类信息，方法信息，参数信息等
 *
 * @author techflowing@gmail.com
 * @since 2022/9/25 17:25
 */
class Request : Parcelable, ISyncRemoteValue {

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
        this.argsWrapper = readParameterWrapperFromParcel(javaClass.classLoader!!, parcel)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(targetClass)
        parcel.writeString(methodName)
        if (flags == Parcelable.PARCELABLE_WRITE_RETURN_VALUE) {
            writeParameterToParcel(parcel, argsWrapper, flags)
        } else {
            parcel.writeParcelableArray(argsWrapper, flags)
        }
    }

    override fun syncRemoteValueFromParcel(source: Parcel) {
        targetClass = source.readString()
        methodName = source.readString()
        readParameterFromParcel(source, argsWrapper)
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

    fun getParameterWrapper(): Array<BaseParameterWrapper>? {
        return argsWrapper
    }

    fun isOneway(): Boolean {
        return oneway
    }

    /**
     * 从 Parcel 中读取出 BaseParameterWrapper
     */
    private fun readParameterWrapperFromParcel(loader: ClassLoader, parcel: Parcel): Array<BaseParameterWrapper>? {
        val size = parcel.readInt()
        if (size < 0) {
            return null
        }
        return Array(size) {
            parcel.readParcelable(loader)!!
        }
    }

    /**
     * 写参数信息到 Parcel
     */
    private fun writeParameterToParcel(dest: Parcel, wrappers: Array<BaseParameterWrapper>?, flags: Int) {
        if (wrappers != null) {
            val size = wrappers.size
            dest.writeInt(size)
            for (index in 0 until size) {
                wrappers[index].writeToParcel(dest, flags)
            }
        } else {
            dest.writeInt(-1)
        }
    }

    /**
     * 读参数信息，从远端返回来的
     */
    private fun readParameterFromParcel(source: Parcel, wrappers: Array<BaseParameterWrapper>?) {
        val size = source.readInt()
        if (size <= 0) {
            return
        }
        wrappers?.forEach { item ->
            item.syncRemoteValueFromParcel(source)
        }
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