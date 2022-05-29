package win.techflowing.android.plugin

import com.android.build.api.instrumentation.ClassContext
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes

/**
 * 插桩实现类
 *
 * @author techflowing@gmail.com
 * @since 2022/5/29 9:51 下午
 */
class ServiceManagerClassVisitor(
    params: PluginParams,
    classContext: ClassContext,
    nextClassVisitor: ClassVisitor
) : ClassVisitor(Opcodes.ASM9, nextClassVisitor) {

}