package win.techflowing.android.plugin.service.visitor

import org.objectweb.asm.AnnotationVisitor
import win.techflowing.service.manager.annotation.Priority
import win.techflowing.service.manager.annotation.Scope

/**
 * ServiceImpl 注解信息扫描
 *
 * @author techflowing@gmail.com
 * @since 2022/6/5 12:40 上午
 */
class ServiceImplAnnotationVisitor(
    api: Int,
    annotationVisitor: AnnotationVisitor,
    private val callback: (priority: Priority, scope: Scope) -> Unit
) : AnnotationVisitor(api, annotationVisitor) {

    companion object {
        const val SCOPE = "scope"
        const val PRIORITY = "priority"
    }

    private var priority: Priority = Priority.DEFAULT
    private var scope: Scope = Scope.SINGLETON

    override fun visitEnum(name: String, descriptor: String, value: String) {
        super.visitEnum(name, descriptor, value)
        if (name == SCOPE) {
            scope = Scope.valueOf(value)
        }
        if (name == PRIORITY) {
            priority = Priority.valueOf(value)
        }
    }

    override fun visitEnd() {
        super.visitEnd()
        callback.invoke(priority, scope)
    }
}