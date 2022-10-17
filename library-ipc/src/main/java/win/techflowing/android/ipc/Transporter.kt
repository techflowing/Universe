package win.techflowing.android.ipc

/**
 * 跨进程方法调用 Binder
 *
 * @author techflowing@gmail.com
 * @since 2022/9/28 23:34
 */
class Transporter private constructor() : ITransporter.Stub() {

    override fun basicTypes(
        anInt: Int,
        aLong: Long,
        aBoolean: Boolean,
        aFloat: Float,
        aDouble: Double,
        aString: String?
    ) {
        TODO("Not yet implemented")
    }

    companion object {

        private val ins by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Transporter()
        }

        fun getInstance(): Transporter {
            return ins
        }
    }
}