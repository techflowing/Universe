package win.techflowing.android.plugin.service

import com.android.build.api.instrumentation.InstrumentationParameters
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import win.techflowing.android.plugin.service.create.Collector
import java.io.File

/**
 * 传递到 ClassVisitor 的参数
 *
 * @author techflowing@gmail.com
 * @since 2022/5/29 9:45 下午
 */
interface PluginParams : InstrumentationParameters {

    @get:Input
    val additionalClassesDir: Property<File>

    @get:Internal
    val scanCollector: Property<Collector>
}