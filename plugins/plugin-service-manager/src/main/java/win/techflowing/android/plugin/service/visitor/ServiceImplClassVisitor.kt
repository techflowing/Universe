package win.techflowing.android.plugin.service.visitor

import com.android.build.api.instrumentation.ClassContext
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes
import win.techflowing.android.plugin.common.log.ILogger
import win.techflowing.android.plugin.service.Constant
import win.techflowing.android.plugin.service.PluginParams
import win.techflowing.android.plugin.common.uitl.ASMUtil
import win.techflowing.android.plugin.common.uitl.ObjectFormatUtil
import java.lang.IllegalStateException

/**
 * Service 实现类 Visitor
 *
 * @author techflowing@gmail.com
 * @since 2022/5/29 9:51 下午
 */
class ServiceImplClassVisitor(
    private val params: PluginParams,
    private val classContext: ClassContext,
    private val logger: ILogger,
    nextClassVisitor: ClassVisitor
) : ClassVisitor(Opcodes.ASM9, nextClassVisitor) {

    companion object {
        const val TAG = "ServiceImplClassVisitor"
    }

    /** 实现的 Service，可能存在多实现的场景 */
    private var serviceList = mutableListOf<String>()

    override fun visit(
        version: Int,
        access: Int,
        name: String,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        super.visit(version, access, name, signature, superName, interfaces)
        if (!ASMUtil.isPublicClass(access)) {
            throw IllegalStateException("被 ${Constant.Annotation.SERVICE_IMPL} 注解的类必须要是 public 的，有问题的类：$name")
        }
        classContext.currentClassData.interfaces.forEach { interfaceClass ->
            val interfaceData = classContext.loadClassData(interfaceClass)
            val isTargetService = interfaceData?.interfaces?.contains(Constant.Class.I_SERVICE)
            if (isTargetService == true) {
                serviceList.add(interfaceData.className)
            }
        }
        if (serviceList.isEmpty()) {
            throw IllegalStateException(
                "被 ${Constant.Annotation.SERVICE_IMPL} 注解的类必须实现实现 继承自 ${Constant.Class.I_SERVICE} 的接口"
            )
        }
        logger.i(TAG, "找到了被 @${Constant.Annotation.SERVICE_IMPL} 注解的类: $name")
    }

    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        val annotationVisitor = super.visitAnnotation(descriptor, visible)
        when (descriptor) {
            ObjectFormatUtil.nameToDesc(Constant.Annotation.SERVICE_IMPL) -> {
                return ServiceImplAnnotationVisitor(api, annotationVisitor, logger) { priority, scope ->
                    val curServiceImpl = classContext.currentClassData.className
                    params.scanCollector.get().onScanServiceImpl(
                        curServiceImpl,
                        serviceList,
                        priority,
                        scope,
                        params.additionalClassesDir.get(),
                        logger
                    )
                }
            }
        }
        return annotationVisitor
    }
}