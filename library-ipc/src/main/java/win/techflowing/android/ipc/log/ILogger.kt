package win.techflowing.android.ipc.log

/**
 * Log 输出接口类
 *
 * @author techflowing@gmail.com
 * @since 2022/7/3 5:04 下午
 */
interface ILogger {

    /**
     * Log a message with level .
     *
     * @param msg the message to log
     */
    fun d(tag: String, msg: String)

    /**
     * Log a message with level .
     *
     * @param msg the message to log
     */
    fun i(tag: String, msg: String)

    /**
     * Log a message with level .
     *
     * @param msg the message to log
     */
    fun w(tag: String, msg: String)

    /**
     * Log a message with level .
     *
     * @param msg the message to log
     */
    fun e(tag: String, msg: String)

    /**
     * Log a message and a throwable with level .
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun e(tag: String, msg: String, tr: Throwable)
}