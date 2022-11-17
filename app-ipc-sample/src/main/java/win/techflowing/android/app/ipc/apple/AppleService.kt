package win.techflowing.android.app.ipc.apple

import android.os.Parcelable
import win.techflowing.android.ipc.IRemoteService
import win.techflowing.android.ipc.annotation.In
import win.techflowing.android.ipc.annotation.InOut

/**
 * Apple Service
 *
 * @author techflowing@gmail.com
 * @since 2022/10/16 22:58
 */
interface AppleService : IRemoteService {

    fun getAppleName(): String

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
        @InOut byteArray: Array<Byte>,
        @InOut shortArray: Array<Short>,
        @InOut intArray: Array<Int>,
        @InOut longArray: Array<Long>,
        @InOut floatArray: Array<Float>,
        @InOut doubleArray: Array<Double>,
        @InOut booleanArray: Array<Boolean>,
        @InOut charArray: Array<Char>
    )

    fun stringType(
        string: String,
        stringArray: Array<String>,
        charSequence: CharSequence,
        charSequenceArray: Array<CharSequence>,
    )

    fun parcelableType(parcelable: Parcelable)

    fun collectType(@InOut list: List<String>, @InOut map: Map<String, String>)
}