package win.techflowing.android.ipc.parameter

import android.os.Parcel

/**
 * 同步远程修改的数据
 *
 * @author techflowing@gmail.com
 * @since 2022/11/18 23:40
 */
interface ISyncRemoteValue {

    /**
     * 远端有返回值时，读取更新本地值，@InOut，@Out 注解的参数
     *
     * @param source 源
     */
    fun syncRemoteValueFromParcel(source: Parcel)
}