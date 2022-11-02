package win.techflowing.android.ipc

import android.app.Application
import android.content.Context
import win.techflowing.android.ipc.call.adapter.CallAdapterFactory
import win.techflowing.android.ipc.log.ILogger

/**
 * IPC 的配置
 *
 * @author techflowing@gmail.com
 * @since 2022/11/1 23:30
 */
class IpcConfiguration internal constructor(private val builder: Builder) {

    /**
     * 获取 Application
     *
     * @return Application
     */
    fun getApplication(): Application {
        return builder.application
    }

    /**
     * 获取 Context
     *
     * @return Context
     */
    fun getContext(): Context {
        return builder.application
    }

    /**
     * 获取日志对象
     *
     * @return 日志打印器
     */
    fun getLogger(): ILogger? {
        return builder.logger
    }

    /**
     * 获取 CallAdapterFactory 集合
     *
     * @return CallAdapterFactory 集合
     */
    fun getAdapterFactory(): List<CallAdapterFactory> {
        return builder.adapterFactoryList
    }

    class Builder(val application: Application) {

        var logger: ILogger? = null
        val adapterFactoryList = mutableListOf<CallAdapterFactory>()

        fun logger(logger: ILogger) = apply {
            this.logger = logger
        }

        fun addAdapterFactory(adapterFactory: CallAdapterFactory) = apply {
            this.adapterFactoryList.add(adapterFactory)
        }

        fun build(): IpcConfiguration {
            return IpcConfiguration(this)
        }
    }
}