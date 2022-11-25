package win.techflowing.android.app.ipc.apple

import win.techflowing.android.ipc.IRemoteCallback

/**
 * 远程回调方法
 *
 * @author techflowing@gmail.com
 * @since 2022/11/24 23:03
 */
interface CountCallback : IRemoteCallback {

    /**
     * 个数统计
     *
     * @param result 个数
     */
    fun count(result: Int)
}