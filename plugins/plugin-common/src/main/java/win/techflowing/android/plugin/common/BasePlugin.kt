package win.techflowing.android.plugin.common

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.Variant
import org.gradle.api.Plugin
import org.gradle.api.Project
import win.techflowing.android.plugin.common.log.LogLevel
import java.io.File
import java.lang.reflect.ParameterizedType

/**
 * Plugin 基类，封装一些常用逻辑，路径配置等
 *
 * @author techflowing@gmail.com
 * @since 2022/6/28 11:57 下午
 */
abstract class BasePlugin<ParamT : BaseParams, Extension : BaseExtension> : Plugin<Project> {

    override fun apply(project: Project) {
        // 获取 extension
        val extension = project.extensions.create(name(), getExtensionClass())
        // 注册 ClassVisitor
        val androidComponents = project.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponents.onVariants { variant ->
            variant.instrumentation.transformClassesWith(
                classVisitorFactory(),
                scope()
            ) {
                @Suppress("UNCHECKED_CAST")
                configCommonParams(it, project, variant, extension as Extension)
                configParams(it, project, variant)
            }
            variant.instrumentation.setAsmFramesComputationMode(asmFramesComputationMode())
            variant.instrumentation.excludes.addAll(excludes())
        }
    }

    /**
     * 获取Extension 的类型
     *
     * @return 实际 Class 类型
     */
    private fun getExtensionClass(): Class<*> {
        return (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<*>
    }

    /**
     * 配置一些公共部分的信息
     *
     * @param paramT 信息承载体
     * @param project Project
     * @param variant 变体
     */
    private fun configCommonParams(paramT: ParamT, project: Project, variant: Variant, extension: Extension) {
        paramT.pluginName.set(name())
        paramT.logTag.set(extension.logTag ?: name())
        paramT.logLevel.set(extension.logLevel ?: LogLevel.DEBUG)
        val classesDir = File(project.buildDir, "intermediates/javac/${variant.name}/classes")
        paramT.additionalClassesDir.set(classesDir)
    }

    open fun asmFramesComputationMode(): FramesComputationMode {
        return FramesComputationMode.COPY_FRAMES
    }

    open fun excludes(): Set<String> {
        return mutableSetOf()
    }

    /** Extension 名称 */
    abstract fun name(): String

    abstract fun scope(): InstrumentationScope

    abstract fun classVisitorFactory(): Class<out AsmClassVisitorFactory<ParamT>>

    abstract fun configParams(param: ParamT, project: Project, variant: Variant)
}