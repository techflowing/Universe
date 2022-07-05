package win.techflowing.android.plugin.common.log

/**
 * Log 输出接口类
 *
 * @author techflowing@gmail.com
 * @since 2022/7/3 5:04 下午
 */
interface ILogger {

    /**
     * Log an object with level [LogLevel.DEBUG].
     *
     * @param any the object to log
     * @since 1.1.0
     */
    fun d(tag: String, any: Any)

    /**
     * Log an array with level [LogLevel.DEBUG].
     *
     * @param array the array to log
     */
    fun d(tag: String, array: Array<Any>)

    /**
     * Log a message with level [LogLevel.DEBUG].
     *
     * @param format the format of the message to log, null if just need
     *     to concat arguments
     * @param args the arguments of the message to log
     */
    fun d(tag: String, format: String, vararg args: Any)

    /**
     * Log a message with level [LogLevel.DEBUG].
     *
     * @param msg the message to log
     */
    fun d(tag: String, msg: String)

    /**
     * Log a message and a throwable with level [LogLevel.DEBUG].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun d(tag: String, msg: String, tr: Throwable)

    /**
     * Log an object with level [LogLevel.INFO].
     *
     * @param any the object to log
     */
    fun i(tag: String, any: Any)

    /**
     * Log an array with level [LogLevel.INFO].
     *
     * @param array the array to log
     */
    fun i(tag: String, array: Array<Any>)

    /**
     * Log a message with level [LogLevel.INFO].
     *
     * @param format the format of the message to log, null if just need
     *     to concat arguments
     * @param args the arguments of the message to log
     */
    fun i(tag: String, format: String, vararg args: Any)

    /**
     * Log a message with level [LogLevel.INFO].
     *
     * @param msg the message to log
     */
    fun i(tag: String, msg: String)

    /**
     * Log a message and a throwable with level [LogLevel.INFO].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun i(tag: String, msg: String, tr: Throwable)

    /**
     * Log an object with level [LogLevel.WARN].
     *
     * @param any the object to log
     */
    fun w(tag: String, any: Any)

    /**
     * Log an array with level [LogLevel.WARN].
     *
     * @param array the array to log
     */
    fun w(tag: String, array: Array<Any>)

    /**
     * Log a message with level [LogLevel.WARN].
     *
     * @param format the format of the message to log, null if just need
     *     to concat arguments
     * @param args the arguments of the message to log
     */
    fun w(tag: String, format: String, vararg args: Any)

    /**
     * Log a message with level [LogLevel.WARN].
     *
     * @param msg the message to log
     */
    fun w(tag: String, msg: String)

    /**
     * Log a message and a throwable with level [LogLevel.WARN].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun w(tag: String, msg: String, tr: Throwable)

    /**
     * Log an object with level [LogLevel.ERROR].
     *
     * @param any the object to log
     */
    fun e(tag: String, any: Any)

    /**
     * Log an array with level [LogLevel.ERROR].
     *
     * @param array the array to log
     */
    fun e(tag: String, array: Array<Any>)

    /**
     * Log a message with level [LogLevel.ERROR].
     *
     * @param format the format of the message to log, null if just need
     *     to concat arguments
     * @param args the arguments of the message to log
     */
    fun e(tag: String, format: String, vararg args: Any)

    /**
     * Log a message with level [LogLevel.ERROR].
     *
     * @param msg the message to log
     */
    fun e(tag: String, msg: String)

    /**
     * Log a message and a throwable with level [LogLevel.ERROR].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun e(tag: String, msg: String, tr: Throwable)
}