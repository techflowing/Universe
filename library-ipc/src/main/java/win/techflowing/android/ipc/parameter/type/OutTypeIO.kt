package win.techflowing.android.ipc.parameter.type

import android.os.Parcel

/**
 * 可用于 @Out 注解的参数
 *
 * @author techflowing@gmail.com
 * @since 2022/11/15 21:57
 */
interface OutTypeIO<T> : TypeIO<T> {

    /**
     * 从 Parcel 中读取数据，写入到给定的对象里
     *
     * @param source 源数据
     * @param target 要写入的数据
     */
    fun readFromParcel(source: Parcel, target: T)
}