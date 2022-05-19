package win.techflowing.android.log

import android.content.Intent
import android.os.Bundle
import win.techflowing.android.log.formatter.*
import win.techflowing.android.log.formatter.default.DefaultJsonFormatter
import win.techflowing.android.log.formatter.default.DefaultThreadFormatter
import win.techflowing.android.log.formatter.default.DefaultThrowableFormatter
import win.techflowing.android.log.formatter.default.DefaultXmlFormatter
import win.techflowing.android.log.formatter.default.objects.BundleFormatter
import win.techflowing.android.log.formatter.default.objects.IntentFormatter
import win.techflowing.android.log.internal.LogLevel

/**
 * Log 工具的配置
 *
 * @author techflowing@gmail.com
 * @since 2022/5/18 11:42 下午
 */
class LogConfiguration internal constructor(builder: Builder) {

    /** 所有日志的统一 TAG 前缀 */
    val tag: String = builder.tag

    /** 日志打印级别 */
    val logLevel: Int = builder.logLevel

    /** 是否打印线程的信息 */
    val withThread: Boolean = builder.withThread

    /** 线程信息格式化 */
    val threadFormatter: ThreadFormatter = builder.threadFormatter!!

    /** JSON 数据格式化. */
    val jsonFormatter: JsonFormatter = builder.jsonFormatter!!

    /** XML 数据格式化. */
    val xmlFormatter: XmlFormatter = builder.xmlFormatter!!

    /** 异常信息格式化 */
    var throwableFormatter: ThrowableFormatter = builder.throwableFormatter!!

    /** 对象信息格式化 */
    val objectFormatterMap: Map<Class<*>, ObjectFormatter<*>> = builder.objectFormatter!!

    /**
     * 根据对象类型获取对象数据格式化
     *
     * @param T 对象类型
     * @param any 对象
     * @return 格式化器
     */
    fun <T> getObjectFormatter(any: T): ObjectFormatter<T>? {
        var clazz: Class<*>?
        var superClazz: Class<*>? = any!!::class.java
        var objectFormatter: ObjectFormatter<*>?
        do {
            clazz = superClazz
            objectFormatter = objectFormatterMap[clazz]
            superClazz = clazz!!.superclass
        } while (objectFormatter == null && superClazz != null)
        return objectFormatter as ObjectFormatter<T>?
    }

    /** Builder 类. */
    class Builder {
        internal var tag: String = ""
        internal var logLevel: Int = LogLevel.ALL
        internal var withThread: Boolean = false
        internal var threadFormatter: ThreadFormatter? = null
        internal var jsonFormatter: JsonFormatter? = null
        internal var xmlFormatter: XmlFormatter? = null
        internal var throwableFormatter: ThrowableFormatter? = null
        internal var objectFormatter: MutableMap<Class<*>, ObjectFormatter<*>>? = null

        fun tag(tag: String) = apply {
            this.tag = tag
        }

        fun logLevel(logLevel: Int) = apply {
            this.logLevel = logLevel
        }

        fun withThread(with: Boolean) = apply {
            this.withThread = with
        }

        fun threadFormatter(threadFormatter: ThreadFormatter) = apply {
            this.threadFormatter = threadFormatter
        }

        fun jsonFormatter(jsonFormatter: JsonFormatter) = apply {
            this.jsonFormatter = jsonFormatter
        }

        fun xmlFormatter(xmlFormatter: XmlFormatter) = apply {
            this.xmlFormatter = xmlFormatter
        }

        fun throwableFormatter(throwableFormatter: ThrowableFormatter) = apply {
            this.throwableFormatter = throwableFormatter
        }

        fun <T> objectFormatter(clazz: Class<T>, formatter: ObjectFormatter<T>) = apply {
            if (objectFormatter == null) {
                objectFormatter = buildDefaultObjectFormatterMap()
            }
            objectFormatter?.put(clazz, formatter)
        }

        fun build(): LogConfiguration {
            initDefaultValueIfNull()
            return LogConfiguration(this)
        }

        /** 未一些必要的变量赋值默认值 */
        private fun initDefaultValueIfNull() {
            if (threadFormatter == null) {
                threadFormatter = DefaultThreadFormatter()
            }
            if (jsonFormatter == null) {
                jsonFormatter = DefaultJsonFormatter()
            }
            if (xmlFormatter == null) {
                xmlFormatter = DefaultXmlFormatter()
            }
            if (throwableFormatter == null) {
                throwableFormatter = DefaultThrowableFormatter()
            }
            if (objectFormatter == null) {
                objectFormatter = buildDefaultObjectFormatterMap()
            }
        }

        /** 构建默认的对象格式化期Map */
        private fun buildDefaultObjectFormatterMap(): MutableMap<Class<*>, ObjectFormatter<*>> {
            return mutableMapOf(
                Bundle::class.java to BundleFormatter(),
                Intent::class.java to IntentFormatter()
            )
        }
    }
}