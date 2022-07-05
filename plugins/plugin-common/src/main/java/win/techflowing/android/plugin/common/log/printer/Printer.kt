package win.techflowing.android.plugin.common.log.printer

import win.techflowing.android.plugin.common.log.LogLevel

/**
 * Log 输出，可实现此接口实现向控制台输出，文件写入等功能
 *
 * @author techflowing@gmail.com
 * @since 2022/5/19 9:06 下午
 */
interface Printer {

    /**
     * 输出Log
     *
     * @param logLevel LOG 级别
     * @param tag TAG
     * @param msg 内容
     */
    fun println(logLevel: LogLevel, tag: String, msg: String)
}