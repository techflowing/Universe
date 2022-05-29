package win.techflowing.android.plugin

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import org.objectweb.asm.ClassVisitor

/**
 * 插桩入口类创建工厂
 *
 * @author techflowing@gmail.com
 * @since 2022/5/29 9:23 下午
 */
abstract class ServiceManagerClassVisitorFactory : AsmClassVisitorFactory<PluginParams> {

    override fun createClassVisitor(classContext: ClassContext, nextClassVisitor: ClassVisitor): ClassVisitor {
        return ServiceManagerClassVisitor(parameters.get(), classContext, nextClassVisitor)
    }

    override fun isInstrumentable(classData: ClassData): Boolean {
        println(classData.className)
        return true
    }
}