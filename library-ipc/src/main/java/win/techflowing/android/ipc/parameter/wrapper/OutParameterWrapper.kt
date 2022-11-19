package win.techflowing.android.ipc.parameter.wrapper

import android.os.Parcel
import android.os.Parcelable
import win.techflowing.android.ipc.parameter.type.OutTypeIO
import win.techflowing.android.ipc.parameter.type.TypeTable

/**
 * @Out 注解类型参数 包装器
 *
 *
 * @author techflowing@gmail.com
 * @since 2022/11/10 00:00
 */
class OutParameterWrapper : BaseParameterWrapper {

    private var type = TypeTable.EMPTY.ordinal
    private var param: Any? = null

    constructor(param: Any?, paramType: Class<*>) {
        this.param = param
        this.type = TypeTable.getTypeIndex(paramType)
    }

    constructor(parcel: Parcel) {
        // TODO: 待补充逻辑
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(type)
        val outType = TypeTable.getTypeIO(type) as OutTypeIO<Any?>
        if (flags == Parcelable.PARCELABLE_WRITE_RETURN_VALUE) {
            outType.writeToParcel(parcel, flags, param)
        } else {
            if (type == TypeTable.PARCELABLE.ordinal) {
                parcel.writeString(param?.javaClass?.name)
            } else if (TypeTable.isArrayType(type)) {
                // 只写入长度
                parcel.writeInt(java.lang.reflect.Array.getLength(param))
            }
        }
    }

    override fun syncRemoteValueFromParcel(source: Parcel) {
        TODO("Not yet implemented")
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

    companion object CREATOR : Parcelable.Creator<OutParameterWrapper> {
        override fun createFromParcel(parcel: Parcel): OutParameterWrapper {
            return OutParameterWrapper(parcel)
        }

        override fun newArray(size: Int): Array<OutParameterWrapper?> {
            return arrayOfNulls(size)
        }
    }
}