package win.techflowing.android.plugin.service

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import org.objectweb.asm.ClassVisitor
import win.techflowing.android.plugin.common.log.PluginLogFactory
import win.techflowing.android.plugin.service.visitor.ServiceImplClassVisitor

/**
 * 插桩入口类创建工厂
 *
 * @author techflowing@gmail.com
 * @since 2022/5/29 9:23 下午
 */
abstract class ServiceManagerClassVisitorFactory : AsmClassVisitorFactory<PluginParams> {

    override fun createClassVisitor(classContext: ClassContext, nextClassVisitor: ClassVisitor): ClassVisitor {
        val params = parameters.get()
        val logger = PluginLogFactory.getLogger(params)
        if (classContext.currentClassData.classAnnotations.contains(Constant.Annotation.SERVICE_IMPL)) {
            return ServiceImplClassVisitor(params, classContext, logger, nextClassVisitor)
        }
        return nextClassVisitor
    }

    override fun isInstrumentable(classData: ClassData): Boolean {
        return classData.classAnnotations.contains(Constant.Annotation.SERVICE_IMPL)
    }
}