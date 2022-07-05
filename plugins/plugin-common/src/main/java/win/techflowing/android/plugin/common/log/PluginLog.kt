package win.techflowing.android.plugin.common.log

import win.techflowing.android.plugin.common.log.printer.Printer
import win.techflowing.android.plugin.common.log.printer.PrinterSet
import java.util.*

/**
 * Log 的实现
 *
 * @author techflowing@gmail.com
 * @since 2022/7/3 5:48 下午
 */
class PluginLog internal constructor(builder: Builder) : ILogger {

    /** 所有日志的统一 TAG 前缀 */
    private val tag: String = builder.tag

    /** 日志打印级别 */
    private val logLevel: LogLevel = builder.logLevel

    /** 日志输出 */
    private var printer: Printer = builder.printer


    class Builder {
        internal var tag: String = ""
        internal var logLevel: LogLevel = LogLevel.ERROR
        internal var printer: Printer = PrinterSet(arrayOf())

        fun tag(tag: String) = apply {
            this.tag = tag
        }

        fun logLevel(logLevel: LogLevel) = apply {
            this.logLevel = logLevel
        }

        fun printer(printer: Printer) = apply {
            this.printer = printer
        }

        fun build(): PluginLog {
            return PluginLog(this)
        }
    }

    /**
     * Log an object with level [LogLevel.DEBUG].
     *
     * @param any the object to log
     * @since 1.1.0
     */
    override fun d(tag: String, any: Any) {
        println(LogLevel.DEBUG, tag, any)
    }

    /**
     * Log an array with level [LogLevel.DEBUG].
     *
     * @param array the array to log
     */
    override fun d(tag: String, array: Array<Any>) {
        println(LogLevel.DEBUG, tag, array)
    }

    /**
     * Log a message with level [LogLevel.DEBUG].
     *
     * @param format the format of the message to log, null if just need
     *     to concat arguments
     * @param args the arguments of the message to log
     */
    override fun d(tag: String, format: String, vararg args: Any) {
        println(LogLevel.DEBUG, tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.DEBUG].
     *
     * @param msg the message to log
     */
    override fun d(tag: String, msg: String) {
        println(LogLevel.DEBUG, tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.DEBUG].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    override fun d(tag: String, msg: String, tr: Throwable) {
        println(LogLevel.DEBUG, tag, msg, tr)
    }

    /**
     * Log an object with level [LogLevel.INFO].
     *
     * @param any the object to log
     */
    override fun i(tag: String, any: Any) {
        println(LogLevel.INFO, tag, any)
    }

    /**
     * Log an array with level [LogLevel.INFO].
     *
     * @param array the array to log
     */
    override fun i(tag: String, array: Array<Any>) {
        println(LogLevel.INFO, tag, array)
    }

    /**
     * Log a message with level [LogLevel.INFO].
     *
     * @param format the format of the message to log, null if just need
     *     to concat arguments
     * @param args the arguments of the message to log
     */
    override fun i(tag: String, format: String, vararg args: Any) {
        println(LogLevel.INFO, tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.INFO].
     *
     * @param msg the message to log
     */
    override fun i(tag: String, msg: String) {
        println(LogLevel.INFO, tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.INFO].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    override fun i(tag: String, msg: String, tr: Throwable) {
        println(LogLevel.INFO, tag, msg, tr)
    }

    /**
     * Log an object with level [LogLevel.WARN].
     *
     * @param any the object to log
     */
    override fun w(tag: String, any: Any) {
        println(LogLevel.WARN, tag, any)
    }

    /**
     * Log an array with level [LogLevel.WARN].
     *
     * @param array the array to log
     */
    override fun w(tag: String, array: Array<Any>) {
        println(LogLevel.WARN, tag, array)
    }

    /**
     * Log a message with level [LogLevel.WARN].
     *
     * @param format the format of the message to log, null if just need
     *     to concat arguments
     * @param args the arguments of the message to log
     */
    override fun w(tag: String, format: String, vararg args: Any) {
        println(LogLevel.WARN, tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.WARN].
     *
     * @param msg the message to log
     */
    override fun w(tag: String, msg: String) {
        println(LogLevel.WARN, tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.WARN].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    override fun w(tag: String, msg: String, tr: Throwable) {
        println(LogLevel.WARN, tag, msg, tr)
    }

    /**
     * Log an object with level [LogLevel.ERROR].
     *
     * @param any the object to log
     */
    override fun e(tag: String, any: Any) {
        println(LogLevel.ERROR, tag, any)
    }

    /**
     * Log an array with level [LogLevel.ERROR].
     *
     * @param array the array to log
     */
    override fun e(tag: String, array: Array<Any>) {
        println(LogLevel.ERROR, tag, array)
    }

    /**
     * Log a message with level [LogLevel.ERROR].
     *
     * @param format the format of the message to log, null if just need
     *     to concat arguments
     * @param args the arguments of the message to log
     */
    override fun e(tag: String, format: String, vararg args: Any) {
        println(LogLevel.ERROR, tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.ERROR].
     *
     * @param msg the message to log
     */
    override fun e(tag: String, msg: String) {
        println(LogLevel.ERROR, tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.ERROR].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    override fun e(tag: String, msg: String, tr: Throwable) {
        println(LogLevel.ERROR, tag, msg, tr)
    }

    /**
     * Print a log in a new line.
     *
     * @param logLevel the log level of the printing log
     * @param msg the message you would like to log
     */
    private fun println(logLevel: LogLevel, tag: String, msg: String) {
        if (logLevel.level < this.logLevel.level) {
            return
        }
        printlnInternal(logLevel, tag, msg)
    }

    /**
     * Print an object in a new line.
     *
     * @param logLevel the log level of the printing object
     * @param any the object to print
     */
    private fun <T> println(logLevel: LogLevel, tag: String, any: T) {
        if (logLevel.level < this.logLevel.level) {
            return
        }
        printlnInternal(logLevel, tag, any?.toString() ?: "null")
    }

    /**
     * Print a log in a new line.
     *
     * @param logLevel the log level of the printing log
     * @param format the format of the printing log, null if just need
     *     to concat arguments
     * @param args the arguments of the printing log
     */
    private fun println(logLevel: LogLevel, tag: String, format: String, vararg args: Any) {
        if (logLevel.level < this.logLevel.level) {
            return
        }
        val finalString = try {
            String.format(format, *args)
        } catch (e: IllegalFormatConversionException) {
            "$format : ${args.contentToString()}"
        }
        printlnInternal(logLevel, tag, finalString)
    }

    /**
     * Print an array in a new line.
     *
     * @param logLevel the log level of the printing array
     * @param array the array to print
     */
    private fun println(logLevel: LogLevel, tag: String, array: Array<Any>) {
        if (logLevel.level < this.logLevel.level) {
            return
        }
        printlnInternal(logLevel, tag, array.contentDeepToString())
    }

    /**
     * Print a log in a new line internally.
     *
     * @param logLevel the log level of the printing log
     * @param msg the message you would like to log
     */
    private fun printlnInternal(logLevel: LogLevel, tag: String, msg: String) {
        val finalTag = "${this.tag}-$tag"
        printer.println(logLevel, finalTag, msg)
    }
}