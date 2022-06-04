package win.techflowing.android.plugin.service.visitor

import org.objectweb.asm.AnnotationVisitor
import win.techflowing.service.annotation.Priority
import win.techflowing.service.annotation.Scope

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

    override fun visit(name: String?, value: Any?) {
        super.visit(name, value)
        if (name == SCOPE) {
            println(value?.javaClass)
            println(value)
        }
        if (name == PRIORITY) {
            println(value?.javaClass)
            println(value)
        }
    }

    override fun visitEnd() {
        super.visitEnd()
        callback.invoke(Priority.DEFAULT, Scope.SINGLETON)
    }
}