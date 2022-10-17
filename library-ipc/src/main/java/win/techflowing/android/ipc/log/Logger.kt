package win.techflowing.android.ipc.log

/**
 * Logger 日志输出类
 *
 * @author techflowing@gmail.com
 * @since 2022/9/25 18:06
 */
object Logger : ILogger {

    private const val TAG = "IPC"
    private var logger: ILogger = DefaultIpcLogger()

    override fun d(tag: String, msg: String) {
        logger.d("$TAG-$tag", msg)
    }

    override fun i(tag: String, msg: String) {
        logger.i("$TAG-$tag", msg)
    }

    override fun w(tag: String, msg: String) {
        logger.w("$TAG-$tag", msg)
    }

    override fun e(tag: String, msg: String) {
        logger.e("$TAG-$tag", msg)
    }

    override fun e(tag: String, msg: String, tr: Throwable) {
        logger.e("$TAG-$tag", msg, tr)
    }
}