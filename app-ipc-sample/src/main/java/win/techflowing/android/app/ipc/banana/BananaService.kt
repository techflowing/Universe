package win.techflowing.android.app.ipc.banana

import win.techflowing.android.ipc.IRemoteService

/**
 * Apple Service
 *
 * @author techflowing@gmail.com
 * @since 2022/10/16 22:58
 */
interface BananaService : IRemoteService {

    fun getBananaName():String
}