package win.techflowing.android.app.ipc.apple

import win.techflowing.android.log.XLog

/**
 * Apple Service 实现
 *
 * @author techflowing@gmail.com
 * @since 2022/10/16 22:59
 */
class AppleServiceImpl : AppleService
{

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
        XLog.e(TAG, "接受到基础参数方法调用")
    }

    override fun arrayType(strArray: Array<String>, intArray: Array<Int>) {
        XLog.e(TAG, "接受到数组参数方法调用")
    }

    override fun collectType(list: List<String>) {
        XLog.e(TAG, "接受到集合参数方法调用")
    }

    override fun <T> genericType(list: List<T>, param: T) {
        XLog.e(TAG, "不支持")
    }

    override fun <T> genericArrayType(array: Array<T>) {
        XLog.e(TAG, "不支持")
    }
}