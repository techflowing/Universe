package win.techflowing.android.base.runtime

import android.app.Application

/**
 * APP 运行环境
 */
object AppRuntime {

    /**应用Application*/
    private lateinit var application: Application

    /**是否Debug 环境*/
    private var debug: Boolean = false

    /**
     * 初始化调用
     * @param application 应用Application
     * @param debug 是否Debug 环境
     */
    internal fun init(application: Application, debug: Boolean) {
        this.application = application
        this.debug = debug
    }

    @JvmStatic
    fun getApplication(): Application {
        return application
    }

    @JvmStatic
    fun isDebug(): Boolean {
        return debug
    }
}