package win.techflowing.android.app.ipc.apple

import win.techflowing.android.ipc.IRemoteService

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

    fun arrayType(
        strArray: Array<String>,
        intArray: Array<Int>
    )

    fun collectType(
        list: List<String>
    )

    fun <T> genericType(list: List<T>, param: T)

    fun <T> genericArrayType(array: Array<T>)
}