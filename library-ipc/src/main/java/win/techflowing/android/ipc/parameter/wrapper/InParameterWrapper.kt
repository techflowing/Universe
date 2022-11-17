package win.techflowing.android.ipc.parameter.wrapper

import android.os.Parcel
import android.os.Parcelable
import win.techflowing.android.ipc.parameter.type.TypeIO
import win.techflowing.android.ipc.parameter.type.TypeTable

/**
 * @In 注解类型参数 包装器
 *
 * @author techflowing@gmail.com
 * @since 2022/11/9 23:59
 */
class InParameterWrapper : BaseParameterWrapper {

    private var type = TypeTable.EMPTY.ordinal
    private var param: Any? = null

    constructor(param: Any?, paramType: Class<*>) {
        this.param = param
        this.type = TypeTable.getTypeIndex(paramType)
    }

    constructor(parcel: Parcel) {
        type = parcel.readInt()
        val typeIO = TypeTable.getTypeIO(type)
        param = typeIO.createFromParcel(parcel)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // @In 注解数据单向流通，不回写
        if (flags == Parcelable.PARCELABLE_WRITE_RETURN_VALUE) {
            return
        }
        parcel.writeInt(type)
        val typeIO = TypeTable.getTypeIO(type) as TypeIO<Any?>
        typeIO.writeToParcel(parcel, flags, param)
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

    companion object CREATOR : Parcelable.Creator<InParameterWrapper> {
        override fun createFromParcel(parcel: Parcel): InParameterWrapper {
            return InParameterWrapper(parcel)
        }

        override fun newArray(size: Int): Array<InParameterWrapper?> {
            return arrayOfNulls(size)
        }
    }
}