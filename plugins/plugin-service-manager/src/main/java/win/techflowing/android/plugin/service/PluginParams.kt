package win.techflowing.android.plugin.service

import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import win.techflowing.android.plugin.common.BaseParams
import win.techflowing.android.plugin.service.create.Collector

/**
 * 传递到 ClassVisitor 的参数
 *
 * @author techflowing@gmail.com
 * @since 2022/5/29 9:45 下午
 */
interface PluginParams : BaseParams {

    @get:Internal
    val scanCollector: Property<Collector>
}