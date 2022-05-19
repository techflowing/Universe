package win.techflowing.android.log

import win.techflowing.android.log.internal.LogLevel
import win.techflowing.android.log.internal.Logger
import win.techflowing.android.log.internal.Platform
import win.techflowing.android.log.printer.AndroidPrinter
import win.techflowing.android.log.printer.Printer
import win.techflowing.android.log.printer.PrinterSet

/**
 * Log 工具对外的统一门面
 *
 * @author techflowing@gmail.com
 * @since 2022/5/18 11:42 下午
 */
object XLog {

    /** 是否已经执行过初始化 */
    private var isInitialized: Boolean = false

    /** 配置信息 */
    private lateinit var logConfiguration: LogConfiguration

    /** Log Printer. */
    private lateinit var printerSet: PrinterSet

    private lateinit var logger: Logger

    /** 初始化 */
    @JvmStatic
    fun init() {
        init(LogConfiguration.Builder().build())
    }

    /**
     * 初始化
     *
     * @param configuration 配置信息
     * @param printer Printer 集合
     */
    @JvmStatic
    fun init(configuration: LogConfiguration, vararg printer: Printer) {
        if (isInitialized) {
            Platform.get().warn("XLog is already initialized, do not initialize again")
        }
        isInitialized = true
        logConfiguration = configuration
        printerSet = if (printer.isEmpty()) {
            PrinterSet(arrayOf(AndroidPrinter()))
        } else {
            PrinterSet(printer)
        }
        logger = Logger(logConfiguration, printerSet)
    }

    /**
     * Log an object with level [LogLevel.VERBOSE].
     *
     * @param any the object to log
     */
    fun v(tag: String, any: Any) {
        assertInitialization()
        logger.v(tag, any)
    }

    /**
     * Log an array with level [LogLevel.VERBOSE].
     *
     * @param array the array to log
     */
    fun v(tag: String, array: Array<Any>) {
        assertInitialization()
        logger.v(tag, array)
    }

    /**
     * Log a message with level [LogLevel.VERBOSE].
     *
     * @param format the format of the message to log
     * @param args the arguments of the message to log
     */
    fun v(tag: String, format: String, vararg args: Any) {
        assertInitialization()
        logger.v(tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.VERBOSE].
     *
     * @param msg the message to log
     */
    fun v(tag: String, msg: String) {
        assertInitialization()
        logger.v(tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.VERBOSE].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun v(tag: String, msg: String, tr: Throwable) {
        assertInitialization()
        logger.v(tag, msg, tr)
    }

    /**
     * Log an object with level [LogLevel.DEBUG].
     *
     * @param any the object to log
     * @since 1.1.0
     */
    fun d(tag: String, any: Any) {
        assertInitialization()
        logger.d(tag, any)
    }

    /**
     * Log an array with level [LogLevel.DEBUG].
     *
     * @param array the array to log
     */
    fun d(tag: String, array: Array<Any>) {
        assertInitialization()
        logger.d(tag, array)
    }

    /**
     * Log a message with level [LogLevel.DEBUG].
     *
     * @param format the format of the message to log
     * @param args the arguments of the message to log
     */
    fun d(tag: String, format: String, vararg args: Any) {
        assertInitialization()
        logger.d(tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.DEBUG].
     *
     * @param msg the message to log
     */
    fun d(tag: String, msg: String) {
        assertInitialization()
        logger.d(tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.DEBUG].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun d(tag: String, msg: String, tr: Throwable) {
        assertInitialization()
        logger.d(tag, msg, tr)
    }

    /**
     * Log an object with level [LogLevel.INFO].
     *
     * @param any the object to log
     * @see LogConfiguration.Builder.addObjectFormatter
     * @since 1.1.0
     */
    fun i(tag: String, any: Any) {
        assertInitialization()
        logger.i(tag, any)
    }

    /**
     * Log an array with level [LogLevel.INFO].
     *
     * @param array the array to log
     */
    fun i(tag: String, array: Array<Any>) {
        assertInitialization()
        logger.i(tag, array)
    }

    /**
     * Log a message with level [LogLevel.INFO].
     *
     * @param format the format of the message to log
     * @param args the arguments of the message to log
     */
    fun i(tag: String, format: String, vararg args: Any) {
        assertInitialization()
        logger.i(tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.INFO].
     *
     * @param msg the message to log
     */
    fun i(tag: String, msg: String) {
        assertInitialization()
        logger.i(tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.INFO].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun i(tag: String, msg: String, tr: Throwable) {
        assertInitialization()
        logger.i(tag, msg, tr)
    }

    /**
     * Log an object with level [LogLevel.WARN].
     *
     * @param any the object to log
     * @since 1.1.0
     */
    fun w(tag: String, any: Any) {
        assertInitialization()
        logger.w(tag, any)
    }

    /**
     * Log an array with level [LogLevel.WARN].
     *
     * @param array the array to log
     */
    fun w(tag: String, array: Array<Any>) {
        assertInitialization()
        logger.w(tag, array)
    }

    /**
     * Log a message with level [LogLevel.WARN].
     *
     * @param format the format of the message to log
     * @param args the arguments of the message to log
     */
    fun w(tag: String, format: String, vararg args: Any) {
        assertInitialization()
        logger.w(tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.WARN].
     *
     * @param msg the message to log
     */
    fun w(tag: String, msg: String) {
        assertInitialization()
        logger.w(tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.WARN].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun w(tag: String, msg: String, tr: Throwable) {
        assertInitialization()
        logger.w(tag, msg, tr)
    }

    /**
     * Log an object with level [LogLevel.ERROR].
     *
     * @param any the object to log
     * @since 1.1.0
     */
    fun e(tag: String, any: Any) {
        assertInitialization()
        logger.e(tag, any)
    }

    /**
     * Log an array with level [LogLevel.ERROR].
     *
     * @param array the array to log
     */
    fun e(tag: String, array: Array<Any>) {
        assertInitialization()
        logger.e(tag, array)
    }

    /**
     * Log a message with level [LogLevel.ERROR].
     *
     * @param format the format of the message to log
     * @param args the arguments of the message to log
     */
    fun e(tag: String, format: String, vararg args: Any) {
        assertInitialization()
        logger.e(tag, format, *args)
    }

    /**
     * Log a message with level [LogLevel.ERROR].
     *
     * @param msg the message to log
     */
    fun e(tag: String, msg: String) {
        assertInitialization()
        logger.e(tag, msg)
    }

    /**
     * Log a message and a throwable with level [LogLevel.ERROR].
     *
     * @param msg the message to log
     * @param tr the throwable to be log
     */
    fun e(tag: String, msg: String, tr: Throwable) {
        assertInitialization()
        logger.e(tag, msg, tr)
    }

    /**
     * Log a JSON string, with level [LogLevel.DEBUG] by default.
     *
     * @param json the JSON string to log
     */
    fun json(tag: String, json: String) {
        assertInitialization()
        logger.json(tag, json)
    }

    /**
     * Log a JSON string, with level [LogLevel.INFO] by default.
     *
     * @param json the JSON string to log
     */
    fun jsonForInfo(tag: String, json: String) {
        assertInitialization()
        logger.jsonForInfo(tag, json)
    }

    /**
     * Log a JSON string, with level [LogLevel.ERROR] by default.
     *
     * @param json the JSON string to log
     */
    fun jsonForError(tag: String, json: String) {
        assertInitialization()
        logger.jsonForError(tag, json)
    }

    /**
     * Log a XML string, with level [LogLevel.DEBUG] by default.
     *
     * @param xml the XML string to log
     */
    fun xml(tag: String, xml: String) {
        assertInitialization()
        logger.xml(tag, xml)
    }

    /**
     * Log a XML string, with level [LogLevel.INFO] by default.
     *
     * @param xml the XML string to log
     */
    fun xmlForInfo(tag: String, xml: String) {
        assertInitialization()
        logger.xmlForInfo(tag, xml)
    }

    /**
     * Log a XML string, with level [LogLevel.ERROR] by default.
     *
     * @param xml the XML string to log
     */
    fun xmlForError(tag: String, xml: String) {
        assertInitialization()
        logger.xmlForError(tag, xml)
    }

    /** 强制判断是否初始化 */
    private fun assertInitialization() {
        if (!isInitialized) {
            throw IllegalStateException("Do you forget to initialize XLog?")
        }
    }
}