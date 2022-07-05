package win.techflowing.android.plugin.common

import com.android.build.api.instrumentation.InstrumentationParameters
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import win.techflowing.android.plugin.common.log.LogLevel
import java.io.File

/**
 * InstrumentationParameters 基类
 *
 * @author techflowing@gmail.com
 * @since 2022/6/29 12:13 上午
 */
interface BaseParams : InstrumentationParameters {

    /** Plugin 名称 */
    @get:Internal
    val pluginName: Property<String>

    /** Log 统一前缀 */
    @get:Internal
    val logTag: Property<String>

    /** Log 日志级别，默认全输出 */
    @get:Internal
    val logLevel: Property<LogLevel>

    /** 新增Class的输出位置 */
    @get:Input
    val additionalClassesDir: Property<File>
}