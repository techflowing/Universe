package win.techflowing.android.log.internal

/**
 * 平台能力抽象，当前只支持 Android
 *
 * @author techflowing@gmail.com
 * @since 2022/5/19 1:29 上午
 */
abstract class Platform {

    companion object {
        const val TAG = "XLog"
        private val ANDROID_PLATFORM = AndroidPlatform()

        fun get(): Platform {
            return ANDROID_PLATFORM
        }
    }

    abstract fun warn(message: String)

    abstract fun error(message: String)
}