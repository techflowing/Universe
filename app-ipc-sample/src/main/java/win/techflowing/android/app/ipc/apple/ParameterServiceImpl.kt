package win.techflowing.android.app.ipc.apple

import win.techflowing.android.ipc.call.Call
import win.techflowing.android.ipc.call.adapter.CallWrapper
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

    override fun parcelableType(parcelable: ParcelableModel, parcelableArray: Array<ParcelableModel>) {
        XLog.d(TAG, "接受到 Parcelable 类型参数方法调用")
        XLog.d(TAG, parcelable.toString())
        parcelable.setAge(20)
        XLog.d(TAG, parcelableArray.contentToString())
        parcelableArray.forEach {
            it.setAge(it.getAge() + 10)
        }
    }

    override fun collectType(list: MutableList<String>, map: Map<String, String>) {
        XLog.d(TAG, "接受到 集合类 类型参数方法调用")
        XLog.d(TAG, list)
        list.add("添加的元素")
        XLog.d(TAG, map)
    }

    override fun outParameter(intArray: Array<Int?>, parcelable: ParcelableModel, list: MutableList<String>) {
        XLog.d(TAG, "接受到 @Out 类型参数方法调用")
        XLog.d(TAG, "数据：${intArray.contentToString()}")
        XLog.d(TAG, "数据：$parcelable")
        XLog.d(TAG, "数据：$list")
        for (index in intArray.indices) {
            intArray[index] = index * 100
        }
        parcelable.setAge(100)
        list.add("remote String 1")
        list.add("remote String 2")
    }

    override fun callbackParameter(
        intArray: Array<Int?>,
        calculateCallbackOne: CalculateCallback,
        calculateCallbackTwo: CalculateCallback,
        countCallback: CountCallback
    ) {
        var sum = 0
        var multiplication = 1
        intArray.forEach {
            sum += it ?: 0
            multiplication *= it ?: 1
        }
        calculateCallbackOne.sum(sum)
        calculateCallbackOne.multiplication(0)
        calculateCallbackTwo.sum(0)
        calculateCallbackTwo.multiplication(multiplication)
        countCallback.count(intArray.count())
    }

    override fun nullParameter(
        int: Int?,
        intArray: Array<Int?>?,
        string: String?,
        parcelable: ParcelableModel?,
        list: MutableList<String?>?
    ) {
        XLog.d(TAG, "接受到 空 类型参数方法调用")
        XLog.d(TAG, int ?: "Int null")
        XLog.d(TAG, intArray ?: "Int 数组 null")
        XLog.d(TAG, string ?: "String null")
        XLog.d(TAG, parcelable ?: "Parcelable null")
        XLog.d(TAG, list ?: "List null")
    }

    override fun remoteCalculate(intOne: Int, intTwo: Int): Call<Int> {
        XLog.d(TAG, "接受到 Call 类型方法调用")
        XLog.d(TAG, "当前线程名：${Thread.currentThread().name}")
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return CallWrapper(intOne + intTwo)
    }
}