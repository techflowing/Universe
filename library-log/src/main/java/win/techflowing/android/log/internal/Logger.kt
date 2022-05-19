package win.techflowing.android.log.internal

import win.techflowing.android.log.LogConfiguration
import win.techflowing.android.log.formatter.ObjectFormatter
import win.techflowing.android.log.printer.Printer
import java.util.*

/**
 * Logger 真实的实现类
 *
 * @property logConfiguration 配置信息
 * @property printer 输出
 * @author techflowing@gmail.com
 * @since 2022/5/19 9:29 下午
 */
class Logger(
    private val logConfiguration: LogConfiguration,
    private val printer: Printer
) {

    /**
     * Log an object with level [LogLevel.VERBOSE].
     *
     * @param any the object to log
     * @since 1.1.0
     */
    fun v(tag: String, any: Any) {
        println(LogLevel.VERBOSE, tag, any)
    }

    /**
     * Log an array with level [LogLevel.VERBOSE].
     *
     * @param array the array to log
     */
    fun v(tag: String, array: Array<Any>) {
        println(LogLevel.VERBOSE, tag, array)
    }

    /**
     * Log a message with level [LogLevel.VERBOSE].
     *
     * @param format the format of the message to log
     * @param args the arguments of the message to log
     */
    fun v(tag: String, format: String, vararg args: Any) {
        println(LogLevel.VERBOSE, tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.VERBOSE].
     *
     * @param msg the message to log
     */
    fun v(tag: String, msg: String) {
        println(LogLevel.VERBOSE, tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.VERBOSE].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun v(tag: String, msg: String, tr: Throwable) {
        println(LogLevel.VERBOSE, tag, msg, tr)
    }

    /**
     * Log an object with level [LogLevel.DEBUG].
     *
     * @param any the object to log
     * @since 1.1.0
     */
    fun d(tag: String, any: Any) {
        println(LogLevel.DEBUG, tag, any)
    }

    /**
     * Log an array with level [LogLevel.DEBUG].
     *
     * @param array the array to log
     */
    fun d(tag: String, array: Array<Any>) {
        println(LogLevel.DEBUG, tag, array)
    }

    /**
     * Log a message with level [LogLevel.DEBUG].
     *
     * @param format the format of the message to log, null if just need
     *     to concat arguments
     * @param args the arguments of the message to log
     */
    fun d(tag: String, format: String, vararg args: Any) {
        println(LogLevel.DEBUG, tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.DEBUG].
     *
     * @param msg the message to log
     */
    fun d(tag: String, msg: String) {
        println(LogLevel.DEBUG, tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.DEBUG].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun d(tag: String, msg: String, tr: Throwable) {
        println(LogLevel.DEBUG, tag, msg, tr)
    }

    /**
     * Log an object with level [LogLevel.INFO].
     *
     * @param any the object to log
     */
    fun i(tag: String, any: Any) {
        println(LogLevel.INFO, tag, any)
    }

    /**
     * Log an array with level [LogLevel.INFO].
     *
     * @param array the array to log
     */
    fun i(tag: String, array: Array<Any>) {
        println(LogLevel.INFO, tag, array)
    }

    /**
     * Log a message with level [LogLevel.INFO].
     *
     * @param format the format of the message to log, null if just need
     *     to concat arguments
     * @param args the arguments of the message to log
     */
    fun i(tag: String, format: String, vararg args: Any) {
        println(LogLevel.INFO, tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.INFO].
     *
     * @param msg the message to log
     */
    fun i(tag: String, msg: String) {
        println(LogLevel.INFO, tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.INFO].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun i(tag: String, msg: String, tr: Throwable) {
        println(LogLevel.INFO, tag, msg, tr)
    }

    /**
     * Log an object with level [LogLevel.WARN].
     *
     * @param any the object to log
     */
    fun w(tag: String, any: Any) {
        println(LogLevel.WARN, tag, any)
    }

    /**
     * Log an array with level [LogLevel.WARN].
     *
     * @param array the array to log
     */
    fun w(tag: String, array: Array<Any>) {
        println(LogLevel.WARN, tag, array)
    }

    /**
     * Log a message with level [LogLevel.WARN].
     *
     * @param format the format of the message to log, null if just need
     *     to concat arguments
     * @param args the arguments of the message to log
     */
    fun w(tag: String, format: String, vararg args: Any) {
        println(LogLevel.WARN, tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.WARN].
     *
     * @param msg the message to log
     */
    fun w(tag: String, msg: String) {
        println(LogLevel.WARN, tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.WARN].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun w(tag: String, msg: String, tr: Throwable) {
        println(LogLevel.WARN, tag, msg, tr)
    }

    /**
     * Log an object with level [LogLevel.ERROR].
     *
     * @param any the object to log
     */
    fun e(tag: String, any: Any) {
        println(LogLevel.ERROR, tag, any)
    }

    /**
     * Log an array with level [LogLevel.ERROR].
     *
     * @param array the array to log
     */
    fun e(tag: String, array: Array<Any>) {
        println(LogLevel.ERROR, tag, array)
    }

    /**
     * Log a message with level [LogLevel.ERROR].
     *
     * @param format the format of the message to log, null if just need
     *     to concat arguments
     * @param args the arguments of the message to log
     */
    fun e(tag: String, format: String, vararg args: Any) {
        println(LogLevel.ERROR, tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.ERROR].
     *
     * @param msg the message to log
     */
    fun e(tag: String, msg: String) {
        println(LogLevel.ERROR, tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.ERROR].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun e(tag: String, msg: String, tr: Throwable) {
        println(LogLevel.ERROR, tag, msg, tr)
    }

    /**
     * Log a JSON string, with level [LogLevel.DEBUG] by default.
     *
     * @param json the JSON string to log
     */
    fun json(tag: String, json: String) {
        json(LogLevel.DEBUG, tag, json)
    }

    /**
     * Log a JSON string, with level [LogLevel.INFO] by default.
     *
     * @param json the JSON string to log
     */
    fun jsonForInfo(tag: String, json: String) {
        json(LogLevel.INFO, tag, json)
    }

    /**
     * Log a JSON string, with level [LogLevel.ERROR] by default.
     *
     * @param json the JSON string to log
     */
    fun jsonForError(tag: String, json: String) {
        json(LogLevel.ERROR, tag, json)
    }

    /**
     * Log a XML string, with level [LogLevel.DEBUG] by default.
     *
     * @param xml the XML string to log
     */
    fun xml(tag: String, xml: String) {
        xml(LogLevel.DEBUG, tag, xml)
    }

    /**
     * Log a XML string, with level [LogLevel.INFO] by default.
     *
     * @param xml the XML string to log
     */
    fun xmlForInfo(tag: String, xml: String) {
        xml(LogLevel.INFO, tag, xml)
    }

    /**
     * Log a XML string, with level [LogLevel.ERROR] by default.
     *
     * @param xml the XML string to log
     */
    fun xmlForError(tag: String, xml: String) {
        xml(LogLevel.ERROR, tag, xml)
    }

    /**
     * Log a JSON string, with level [LogLevel.DEBUG] by default.
     *
     * @param json the JSON string to log
     */
    private fun json(logLevel: Int, tag: String, json: String) {
        if (logLevel < logConfiguration.logLevel) {
            return
        }
        printlnInternal(logLevel, tag, logConfiguration.jsonFormatter.format(json))
    }

    /**
     * Log a XML string, with level [LogLevel.DEBUG] by default.
     *
     * @param xml the XML string to log
     */
    private fun xml(logLevel: Int, tag: String, xml: String) {
        if (logLevel < logConfiguration.logLevel) {
            return
        }
        printlnInternal(logLevel, tag, logConfiguration.xmlFormatter.format(xml))
    }

    /**
     * Print an array in a new line.
     *
     * @param logLevel the log level of the printing array
     * @param array the array to print
     */
    private fun println(logLevel: Int, tag: String, array: Array<Any>) {
        if (logLevel < logConfiguration.logLevel) {
            return
        }
        printlnInternal(logLevel, tag, array.contentDeepToString())
    }

    /**
     * Print a log in a new line.
     *
     * @param logLevel the log level of the printing log
     * @param format the format of the printing log, null if just need
     *     to concat arguments
     * @param args the arguments of the printing log
     */
    private fun println(logLevel: Int, tag: String, format: String, vararg args: Any) {
        if (logLevel < logConfiguration.logLevel) {
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
     * Print a log in a new line.
     *
     * @param logLevel the log level of the printing log
     * @param msg the message you would like to log
     */
    private fun println(logLevel: Int, tag: String, msg: String) {
        if (logLevel < logConfiguration.logLevel) {
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
    private fun <T> println(logLevel: Int, tag: String, any: T) {
        if (logLevel < logConfiguration.logLevel) {
            return
        }
        val objectString: String = if (any != null) {
            val objectFormatter: ObjectFormatter<T>? = logConfiguration.getObjectFormatter(any)
            objectFormatter?.format(any) ?: any.toString()
        } else {
            "null"
        }
        printlnInternal(logLevel, tag, objectString)
    }

    /**
     * Print a log in a new line.
     *
     * @param logLevel the log level of the printing log
     * @param msg the message you would like to log
     * @param tr a throwable object to log
     */
    private fun println(logLevel: Int, tag: String, msg: String, tr: Throwable) {
        if (logLevel < logConfiguration.logLevel) {
            return
        }
        val finalMsg =
            (if (msg.isEmpty()) "" else msg + System.lineSeparator()) + logConfiguration.throwableFormatter.format(tr)

        printlnInternal(logLevel, tag, finalMsg)
    }

    /**
     * Print a log in a new line internally.
     *
     * @param logLevel the log level of the printing log
     * @param msg the message you would like to log
     */
    private fun printlnInternal(logLevel: Int, tag: String, msg: String) {
        val finalTag = logConfiguration.tag + tag
        val thread = if (logConfiguration.withThread) {
            logConfiguration.threadFormatter.format(Thread.currentThread())
        } else {
            null
        }
        val finalMsg = if (thread != null) {
            "Thread-(${thread})${System.lineSeparator()}${msg}"
        } else {
            msg
        }
        printer.println(logLevel, finalTag, finalMsg)
    }
}