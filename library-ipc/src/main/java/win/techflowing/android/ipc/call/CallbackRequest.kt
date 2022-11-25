package win.techflowing.android.ipc.call

import android.os.Parcel
import android.os.Parcelable
import win.techflowing.android.ipc.parameter.wrapper.BaseParameterWrapper

/**
 * Callback 请求
 *
 * @author techflowing@gmail.com
 * @since 2022/11/25 23:31
 */
class CallbackRequest : Request {

    /** 记录参数位置，用于处理一个方法有多个相同 Callback 参数（Callback 为同一对象）的场景 */
    private var callbackParameterIndex: Int

    constructor(
        id: Int,
        targetClass: String?,
        callbackParameterIndex: Int,
        methodName: String?,
        argsWrapper: Array<BaseParameterWrapper>?,
    ) : super(id, targetClass, methodName, argsWrapper) {
        this.callbackParameterIndex = callbackParameterIndex
    }

    constructor(parcel: Parcel) : super(parcel) {
        this.callbackParameterIndex = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeInt(callbackParameterIndex)
    }

    override fun syncRemoteValueFromParcel(source: Parcel) {
        super.syncRemoteValueFromParcel(source)
        callbackParameterIndex = source.readInt()
    }

    fun getCallbackParameterIndex(): Int {
        return callbackParameterIndex
    }

    companion object CREATOR : Parcelable.Creator<CallbackRequest> {

        override fun createFromParcel(parcel: Parcel): CallbackRequest {
            return CallbackRequest(parcel)
        }

        override fun newArray(size: Int): Array<CallbackRequest?> {
            return arrayOfNulls(size)
        }
    }
}