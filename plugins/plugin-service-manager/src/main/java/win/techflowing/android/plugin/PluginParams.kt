package win.techflowing.android.plugin

import com.android.build.api.instrumentation.InstrumentationParameters
import org.gradle.api.tasks.Input

/**
 * 传递到 ClassVisitor 的参数
 *
 * @author techflowing@gmail.com
 * @since 2022/5/29 9:45 下午
 */
interface PluginParams : InstrumentationParameters {

    @get:Input
    var appName: String
}