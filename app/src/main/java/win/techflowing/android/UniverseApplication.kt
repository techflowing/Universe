package win.techflowing.android

import android.app.Application
import android.content.Context
import win.techflowing.android.base.runtime.AppRuntimeInit
import win.techflowing.android.ipc.IpcConfiguration
import win.techflowing.android.ipc.Tartarus
import win.techflowing.android.log.XLog

/**
 * 应用 Application
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2022/5/18 12:55 上午
 */
class UniverseApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        AppRuntimeInit.init(this, BuildConfig.DEBUG)
    }

    override fun onCreate() {
        super.onCreate()
        initXLog()
        initIpc()
    }

    private fun initIpc() {
        Tartarus.init(IpcConfiguration.Builder(this).build())
    }

    private fun initXLog() {
        XLog.init()
    }
}