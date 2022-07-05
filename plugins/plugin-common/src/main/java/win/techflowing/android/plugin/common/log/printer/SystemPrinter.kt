package win.techflowing.android.plugin.common.log.printer

import win.techflowing.android.plugin.common.log.LogLevel

/**
 * 系统日志输出
 *
 * @author techflowing@gmail.com
 * @since 2022/7/3 6:58 下午
 */
class SystemPrinter : Printer {

    override fun println(logLevel: LogLevel, tag: String, msg: String) {
        println(String.format("%s/%s: %s", logLevel.shortDesc, tag, msg))
    }
}