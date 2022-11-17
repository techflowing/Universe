package win.techflowing.android.ipc.parameter.wrapper

import android.os.Parcel
import android.os.Parcelable
import win.techflowing.android.ipc.parameter.type.TypeTable

/**
 * Callback 类型参数包装
 *
 * @author techflowing@gmail.com
 * @since 2022/11/9 22:45
 */
class CallbackParameterWrapper : BaseParameterWrapper {

    private var className: String? = null

    constructor(className: String) {
        this.className = className
    }

    constructor(parcel: Parcel) {
        this.className = parcel.readString()
    }

    override fun getType(): Int {
        return TypeTable.CALLBACK.ordinal
    }

    override fun getParam(): Any? {
        return null
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(className)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CallbackParameterWrapper> {
        override fun createFromParcel(parcel: Parcel): CallbackParameterWrapper {
            return CallbackParameterWrapper(parcel)
        }

        override fun newArray(size: Int): Array<CallbackParameterWrapper?> {
            return arrayOfNulls(size)
        }
    }
}