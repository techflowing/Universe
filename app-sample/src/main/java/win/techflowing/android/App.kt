package win.techflowing.android

import android.app.Application
import android.content.Context
import win.techflowing.android.runtime.AppRuntimeInit
import win.techflowing.android.runtime.annotation.AppApplication

/**
 * Application
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2020/5/11 1:33 AM
 */
@AppApplication
class App : Application() {

    override fun attachBaseContext(base: Context?) {
        AppRuntimeInit.onApplicationAttachBaseContext(this, BuildConfig.DEBUG)
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}