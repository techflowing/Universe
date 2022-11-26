package win.techflowing.android.app.ipc.apple

import win.techflowing.android.ipc.core.IRemoteService

/**
 * Apple Service
 *
 * @author techflowing@gmail.com
 * @since 2022/10/16 22:58
 */
interface AppleService : IRemoteService {

    fun getAppleName(): String
}