package win.techflowing.android.app.ipc.apple

import android.os.Parcelable
import win.techflowing.android.log.XLog
import java.util.*

/**
 * Apple Service 实现
 *
 * @author techflowing@gmail.com
 * @since 2022/10/16 22:59
 */
class AppleServiceImpl : AppleService {

    companion object {
        const val TAG = "AppleServiceImpl"
    }

    override fun getAppleName(): String {
        return "苹果"
    }

    override fun basicType(
        byte: Byte,
        short: Short,
        int: Int,
        long: Long,
        float: Float,
        double: Double,
        boolean: Boolean,
        char: Char
    ) {
        XLog.d(TAG, "接受到基础类型参数方法调用")
        XLog.d(TAG, "byte 类型：$byte")
        XLog.d(TAG, "short 类型：$short")
        XLog.d(TAG, "int 类型：$int")
        XLog.d(TAG, "long 类型：$long")
        XLog.d(TAG, "float 类型：$float")
        XLog.d(TAG, "double 类型：$double")
        XLog.d(TAG, "boolean 类型：$boolean")
        XLog.d(TAG, "char 类型：$char")
    }

    override fun basicArrayType(
        byteArray: ByteArray,
        shortArray: ShortArray,
        intArray: IntArray,
        longArray: LongArray,
        floatArray: FloatArray,
        doubleArray: DoubleArray,
        booleanArray: BooleanArray,
        charArray: CharArray
    ) {
        XLog.d(TAG, "接受到基础类型数组参数方法调用")
        XLog.d(TAG, "byte 类型数组：${byteArray.contentToString()}")
        XLog.d(TAG, "short 类型数组：${shortArray.contentToString()}")
        XLog.d(TAG, "int 类型数组：${intArray.contentToString()}")
        XLog.d(TAG, "long 类型数组：${longArray.contentToString()}")
        XLog.d(TAG, "float 类型数组：${floatArray.contentToString()}")
        XLog.d(TAG, "double 类型数组：${doubleArray.contentToString()}")
        XLog.d(TAG, "boolean 类型数组：${booleanArray.contentToString()}")
        XLog.d(TAG, "char 类型数组：${charArray.contentToString()}")
    }

    override fun boxArrayType(
        byteArray: Array<Byte>,
        shortArray: Array<Short>,
        intArray: Array<Int>,
        longArray: Array<Long>,
        floatArray: Array<Float>,
        doubleArray: Array<Double>,
        booleanArray: Array<Boolean>,
        charArray: Array<Char>
    ) {
        TODO("Not yet implemented")
    }

    override fun stringType(
        string: String,
        stringArray: Array<String>,
        charSequence: CharSequence,
        charSequenceArray: Array<CharSequence>
    ) {
        TODO("Not yet implemented")
    }

    override fun parcelableType(parcelable: Parcelable) {
        TODO("Not yet implemented")
    }

    override fun collectType(list: List<String>, map: Map<String, String>) {
        TODO("Not yet implemented")
    }
}