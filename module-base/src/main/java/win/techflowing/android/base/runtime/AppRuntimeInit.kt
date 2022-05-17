package win.techflowing.android.base.runtime

import android.app.Application

object AppRuntimeInit {

    @JvmStatic
    fun init(application: Application, debug: Boolean) {
        AppRuntime.init(application, debug)
    }
}