package win.techflowing.android.app.ipc.apple

import win.techflowing.android.ipc.annotation.InOut
import win.techflowing.android.log.XLog

/**
 * 参数测试服务实现
 *
 * @author techflowing@gmail.com
 * @since 2022/11/19 17:11
 */
class ParameterServiceImpl : ParameterService {

    companion object {
        const val TAG = "ParameterServiceImpl"
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
        byteArray: Array<Byte?>,
        shortArray: Array<Short?>,
        intArray: Array<Int?>,
        longArray: Array<Long?>,
        floatArray: Array<Float?>,
        doubleArray: Array<Double?>,
        booleanArray: Array<Boolean?>,
        charArray: Array<Char?>
    ) {
        XLog.d(TAG, "接受到包装类型数组参数方法调用")
        XLog.d(TAG, "Byte 类型数组：${byteArray.contentToString()}")
        byteArray[0] = null
        XLog.d(TAG, "Short 类型数组：${shortArray.contentToString()}")
        shortArray[1] = null
        XLog.d(TAG, "Int 类型数组：${intArray.contentToString()}")
        intArray[2] = null
        XLog.d(TAG, "Long 类型数组：${longArray.contentToString()}")
        longArray[0] = null
        XLog.d(TAG, "Float 类型数组：${floatArray.contentToString()}")
        floatArray[1] = null
        XLog.d(TAG, "Double 类型数组：${doubleArray.contentToString()}")
        doubleArray[2] = null
        XLog.d(TAG, "Boolean 类型数组：${booleanArray.contentToString()}")
        booleanArray[0] = null
        XLog.d(TAG, "Char 类型数组：${charArray.contentToString()}")
        charArray[1] = null
    }

    override fun stringType(
        string: String,
        stringArray: Array<String?>,
        charSequence: CharSequence,
        charSequenceArray: Array<CharSequence?>
    ) {
        XLog.d(TAG, "接受到 String 类型数组参数方法调用")
        XLog.d(TAG, "收到 String 类型参数：$string")
        XLog.d(TAG, "收到 String 数组类型参数：${stringArray.contentToString()}")
        stringArray[0] = null
        XLog.d(TAG, "收到 CharSequence 类型参数：$charSequence")
        XLog.d(TAG, "收到 CharSequence 数组类型参数：${charSequenceArray.contentToString()}")
        charSequenceArray[0] = null
    }

    override fun parcelableType(@InOut parcelable: ParcelableModel, @InOut parcelableArray: Array<ParcelableModel>) {
        XLog.d(TAG, "接受到 Parcelable 类型参数方法调用")
        XLog.d(TAG, parcelable.toString())
        parcelable.setAge(20)
        XLog.d(TAG, parcelableArray.contentToString())
        parcelableArray.forEach {
            it.setAge(it.getAge() + 10)
        }
    }

    override fun collectType(list: List<String>, map: Map<String, String>) {
        TODO("Not yet implemented")
    }
}