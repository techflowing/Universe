package win.techflowing.android.ipc.parameter.wrapper

import android.os.Parcel
import android.os.Parcelable
import win.techflowing.android.ipc.parameter.type.TypeIO
import win.techflowing.android.ipc.parameter.type.TypeTable

/**
 * @InOut 注解的参数包装
 *
 * @author techflowing@gmail.com
 * @since 2022/11/9 22:42
 */
class InOutParameterWrapper : BaseParameterWrapper {

    private var type = TypeTable.EMPTY.ordinal
    private var param: Any? = null

    constructor(param: Any?, paramType: Class<*>) {
        this.param = param
        this.type = TypeTable.getTypeIndex(paramType)
    }

    constructor(parcel: Parcel) {
        type = parcel.readInt()
        val type = TypeTable.getTypeIO(type) as TypeIO<Any?>
        param = type.createFromParcel(parcel)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(type)
        val type = TypeTable.getTypeIO(type) as TypeIO<Any?>
        type.writeToParcel(parcel, flags, param)
    }

    override fun getType(): Int {
        return type
    }

    override fun getParam(): Any? {
        return param
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InOutParameterWrapper> {
        override fun createFromParcel(parcel: Parcel): InOutParameterWrapper {
            return InOutParameterWrapper(parcel)
        }

        override fun newArray(size: Int): Array<InOutParameterWrapper?> {
            return arrayOfNulls(size)
        }
    }
}