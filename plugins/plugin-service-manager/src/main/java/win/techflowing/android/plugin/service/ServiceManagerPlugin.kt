package win.techflowing.android.plugin.service

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.Variant
import org.gradle.api.Project
import win.techflowing.android.plugin.common.BasePlugin
import win.techflowing.android.plugin.service.create.Collector

/**
 * Service Manager Plugin 入口
 *
 * @author techflowing@gmail.com
 * @since 2022/5/27 1:35 上午
 */
class ServiceManagerPlugin : BasePlugin<PluginParams, PluginExtension>() {

    override fun name(): String {
        return "ServiceManager"
    }

    override fun scope(): InstrumentationScope {
        return InstrumentationScope.ALL
    }

    override fun classVisitorFactory(): Class<out AsmClassVisitorFactory<PluginParams>> {
        return ServiceManagerClassVisitorFactory::class.java
    }

    override fun configParams(param: PluginParams, project: Project, variant: Variant) {
        param.scanCollector.set(Collector())
    }

    override fun asmFramesComputationMode(): FramesComputationMode {
        return FramesComputationMode.COPY_FRAMES
    }
}