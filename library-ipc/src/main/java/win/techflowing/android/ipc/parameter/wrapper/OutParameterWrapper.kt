package win.techflowing.android.ipc.parameter.wrapper

import android.os.Parcel
import android.os.Parcelable
import win.techflowing.android.ipc.parameter.type.ArrayTypeIO
import win.techflowing.android.ipc.parameter.type.OutTypeIO
import win.techflowing.android.ipc.parameter.type.TypeTable
import win.techflowing.android.ipc.util.TypeUtil

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
        type = parcel.readInt()
        if (type == TypeTable.PARCELABLE.ordinal) {
            parcel.readString()?.also {
                param = TypeUtil.createObjFromClassName(it)
            }
        } else if (type == TypeTable.PARCELABLE_ARRAY.ordinal) {
            val size = parcel.readInt()
            parcel.readString()?.also {
                param = TypeUtil.createArrayFromComponentType(it, size)
            }
        } else if (type == TypeTable.LIST.ordinal) {
            param = mutableListOf<Any?>()
        } else if (type == TypeTable.MAP.ordinal) {
            param = mutableMapOf<Any, Any?>()
        } else if (TypeTable.isArrayType(type)) {
            val size = parcel.readInt()
            val typeIO = TypeTable.getTypeIO(type) as ArrayTypeIO<Any?>
            param = typeIO.newInstance(size)
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(type)
        val outType = TypeTable.getTypeIO(type) as OutTypeIO<Any?>
        if (flags == Parcelable.PARCELABLE_WRITE_RETURN_VALUE) {
            outType.writeToParcel(dest, flags, param)
        } else {
            if (type == TypeTable.PARCELABLE.ordinal) {
                dest.writeString(param?.javaClass?.name)
            } else if (type == TypeTable.PARCELABLE_ARRAY.ordinal) {
                dest.writeInt(java.lang.reflect.Array.getLength(param))
                dest.writeString(param?.javaClass?.componentType?.name)
            } else if (TypeTable.isArrayType(type)) {
                // 只写入长度
                dest.writeInt(java.lang.reflect.Array.getLength(param))
            }
        }
    }

    override fun syncRemoteValueFromParcel(source: Parcel) {
        type = source.readInt()
        val typeIO = TypeTable.getTypeIO(type) as OutTypeIO<Any?>
        typeIO.syncValueFromParcel(source, param)
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