package win.techflowing.android.ipc.parameter.type

import android.os.Parcel

/**
 * 数据读取、写入接口，针对 Parcel 数据读写
 *
 * @author techflowing@gmail.com
 * @since 2022/11/14 23:42
 */
interface TypeIO<T> {

    /**
     * 写入数据到 Parcel
     *
     * @param dest 目标 Parcel
     * @param flags flags
     * @param value 数据值
     */
    fun writeToParcel(dest: Parcel, flags: Int, value: T)

    /**
     * 从 Parcel 中读取数据
     *
     * @param source Parcel
     * @return
     */
    fun createFromParcel(source: Parcel): T?
}