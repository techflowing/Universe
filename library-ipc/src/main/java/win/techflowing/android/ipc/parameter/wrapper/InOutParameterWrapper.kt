package win.techflowing.android.ipc.parameter.wrapper

import android.os.Parcel
import android.os.Parcelable
import win.techflowing.android.ipc.parameter.type.OutTypeIO
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

    /**
     * 服务端读取数据
     */
    constructor(parcel: Parcel) {
        type = parcel.readInt()
        val typeIO = TypeTable.getTypeIO(type) as TypeIO<Any?>
        param = typeIO.createFromParcel(parcel)
    }

    /**
     * 请求端写入数据
     */
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(type)
        val typeIO = TypeTable.getTypeIO(type) as TypeIO<Any?>
        typeIO.writeToParcel(parcel, flags, param)
    }

    /**
     * 读取服务端返回的数据
     */
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

    companion object CREATOR : Parcelable.Creator<InOutParameterWrapper> {
        override fun createFromParcel(parcel: Parcel): InOutParameterWrapper {
            return InOutParameterWrapper(parcel)
        }

        override fun newArray(size: Int): Array<InOutParameterWrapper?> {
            return arrayOfNulls(size)
        }
    }
}