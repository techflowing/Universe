package win.techflowing.android.plugin

import com.android.build.api.instrumentation.InstrumentationParameters

/**
 * 传递到 ClassVisitor 的参数
 *
 * @author techflowing@gmail.com
 * @since 2022/5/29 9:45 下午
 */
open class PluginParams : PluginExtension(), InstrumentationParameters {
}