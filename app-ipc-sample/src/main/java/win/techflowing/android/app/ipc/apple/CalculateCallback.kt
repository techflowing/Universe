package win.techflowing.android.app.ipc.apple

import win.techflowing.android.ipc.core.IRemoteCallback

/**
 * 远程回调方法
 *
 * @author techflowing@gmail.com
 * @since 2022/11/24 23:03
 */
interface CalculateCallback : IRemoteCallback {

    /**
     * 求和计算
     *
     * @param result 和
     */
    fun sum(result: Int)

    /**
     * 相乘计算
     *
     * @param result 和
     */
    fun multiplication(result: Int)
}