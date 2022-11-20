package win.techflowing.android.app.ipc.apple

import win.techflowing.android.ipc.IRemoteService
import win.techflowing.android.ipc.annotation.In
import win.techflowing.android.ipc.annotation.InOut

/**
 * 参数测试服务
 *
 * @author techflowing@gmail.com
 * @since 2022/11/19 17:10
 */
interface ParameterService : IRemoteService {

    fun basicType(
        byte: Byte,
        short: Short,
        int: Int,
        long: Long,
        float: Float,
        double: Double,
        boolean: Boolean,
        char: Char
    )

    fun basicArrayType(
        @In byteArray: ByteArray,
        @In shortArray: ShortArray,
        @In intArray: IntArray,
        @In longArray: LongArray,
        @In floatArray: FloatArray,
        @In doubleArray: DoubleArray,
        @In booleanArray: BooleanArray,
        @In charArray: CharArray
    )

    fun boxArrayType(
        @InOut byteArray: Array<Byte?>,
        @InOut shortArray: Array<Short?>,
        @InOut intArray: Array<Int?>,
        @InOut longArray: Array<Long?>,
        @InOut floatArray: Array<Float?>,
        @InOut doubleArray: Array<Double?>,
        @InOut booleanArray: Array<Boolean?>,
        @InOut charArray: Array<Char?>
    )

    fun stringType(
        @In string: String,
        @InOut stringArray: Array<String?>,
        @In charSequence: CharSequence,
        @InOut charSequenceArray: Array<CharSequence?>,
    )

    fun parcelableType(@InOut parcelable: ParcelableModel, @InOut parcelableArray: Array<ParcelableModel>)

    fun collectType(@InOut list: List<String>, @InOut map: Map<String, String>)
}