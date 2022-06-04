package win.techflowing.android.plugin.service

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Service Manager Plugin 入口
 *
 * @author techflowing@gmail.com
 * @since 2022/5/27 1:35 上午
 */
class ServiceManagerPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        // 解析参数
        val pluginExtension = project.extensions.create(PluginExtension.NAME, PluginExtension::class.java)
        // 注册 ClassVisitor
        val androidComponents = project.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponents.onVariants { variant ->
            variant.instrumentation.transformClassesWith(
                ServiceManagerClassVisitorFactory::class.java,
                InstrumentationScope.ALL
            ) {
                // 设置外部传入参数
                it.appName = pluginExtension.appName ?: "测试"
            }
            variant.instrumentation.setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES)
        }
    }
}