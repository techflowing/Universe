package win.techflowing.android.plugin.common

import win.techflowing.android.plugin.common.log.LogLevel

/**
 * 自定义 Plugin 参数基类
 *
 * @author techflowing@gmail.com
 * @since 2022/6/29 12:22 上午
 */
abstract class BaseExtension {

    /** Log 统一前缀 */
    var logTag: String? = null

    /** Log 日志级别，默认全输出 */
    var logLevel: LogLevel? = null
}