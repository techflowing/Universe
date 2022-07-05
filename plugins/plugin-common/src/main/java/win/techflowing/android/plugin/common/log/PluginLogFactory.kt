package win.techflowing.android.plugin.common.log

import win.techflowing.android.plugin.common.BaseParams
import win.techflowing.android.plugin.common.log.printer.PrinterSet
import win.techflowing.android.plugin.common.log.printer.SystemPrinter

/**
 * PluginLog 创建工厂
 *
 * @author techflowing@gmail.com
 * @since 2022/7/3 10:46 下午
 */
object PluginLogFactory {

    /** 缓存Map */
    private val cacheMap = mutableMapOf<String, ILogger>()

    /**
     * 获取日志打印器
     *
     * @param params Plugin 参数
     * @return
     */
    fun <Params : BaseParams> getLogger(params: Params): ILogger {
        val key = getKey(params.javaClass, params.logTag.get(), params.logLevel.get())
        return cacheMap.getOrPut(key) {
            val printers = arrayOf(SystemPrinter())
            return PluginLog.Builder()
                .tag(params.logTag.get())
                .logLevel(params.logLevel.get())
                .printer(PrinterSet(printers))
                .build()
        }
    }

    /**
     * 计算缓存Key
     *
     * @param clazz 打印Log的对象类
     * @param extension Plugin 配置信息
     * @return
     */
    private fun getKey(clazz: Class<*>, logTag: String, logLevel: LogLevel): String {
        return "${clazz.name}:${logTag}:${logLevel.desc}"
    }
}