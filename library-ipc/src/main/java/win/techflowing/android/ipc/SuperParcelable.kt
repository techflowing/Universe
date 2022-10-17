package win.techflowing.android.ipc

import android.os.Parcel
import android.os.Parcelable

/**
 * Parcelable 扩展接口定义，增加 readFromParcel 方法
 *
 * @author techflowing@gmail.com
 * @since 2022/9/25 15:32
 */
interface SuperParcelable : Parcelable {

    /**
     * 读取 Parcel 中的内容
     *
     * @param parcel Parcel 对象
     */
    fun readFromParcel(parcel: Parcel)
}