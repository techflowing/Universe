package win.techflowing.android.ipc.parameter.wrapper

import android.os.Parcelable
import win.techflowing.android.ipc.parameter.ISyncRemoteValue

/**
 * 参数封装基类
 *
 * @author techflowing@gmail.com
 * @since 2022/10/26 22:20
 */
interface BaseParameterWrapper : Parcelable, ISyncRemoteValue {

    fun getType(): Int

    fun getParam(): Any?
}