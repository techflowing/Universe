package win.techflowing.android.log.formatter.default

import win.techflowing.android.log.formatter.ThrowableFormatter
import java.io.PrintWriter
import java.io.StringWriter

/**
 * 异常信息格式化默认实现
 *
 * @author techflowing@gmail.com
 * @since 2022/5/19 10:05 下午
 */
class DefaultThrowableFormatter : ThrowableFormatter {

    override fun format(data: Throwable): String {
        return getStackTraceString(data)
    }

    private fun getStackTraceString(throwable: Throwable): String {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        throwable.printStackTrace(pw)
        pw.flush()
        return sw.toString()
    }
}