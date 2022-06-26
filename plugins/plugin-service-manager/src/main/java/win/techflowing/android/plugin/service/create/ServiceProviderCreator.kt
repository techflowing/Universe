package win.techflowing.android.plugin.service.create

import org.apache.commons.io.FileUtils
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import win.techflowing.android.plugin.common.uitl.ObjectFormatUtil
import win.techflowing.android.plugin.service.Constant
import win.techflowing.service.manager.annotation.Scope
import java.io.File
import java.lang.IllegalStateException

/**
 * 创建 ServiceProvider 类
 *
 * @author techflowing@gmail.com
 * @since 2022/6/25 12:24 上午
 */
class ServiceProviderCreator {

    /**
     * 生成类
     *
     * @param service Service 名称
     * @param serviceImpl Service 实现类名称
     * @param scope Scope
     * @param output 输出文件夹
     */
    fun createServiceProvider(service: String, serviceImpl: String, scope: Scope, output: File) {
        val providerClassName = getServiceProviderClassName(service)
        val superClassName = getServiceProviderSuperClassName(scope)
        val classWriter = ClassWriter(ClassWriter.COMPUTE_FRAMES or ClassWriter.COMPUTE_MAXS)
        // 创建类信息
        classWriter.visit(
            Constant.ASM_VERSION,
            Opcodes.ACC_PUBLIC or Opcodes.ACC_SUPER,
            ObjectFormatUtil.nameToAsmName(providerClassName),
            getServiceProviderClassSignature(serviceImpl, scope),
            ObjectFormatUtil.nameToAsmName(superClassName),
            null
        )
        // 创建默认构造方法
        var methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null)
        methodVisitor.visitCode()
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
        methodVisitor.visitMethodInsn(
            Opcodes.INVOKESPECIAL,
            ObjectFormatUtil.nameToAsmName(superClassName),
            "<init>",
            "()V",
            false
        )
        methodVisitor.visitInsn(Opcodes.RETURN)
        methodVisitor.visitEnd()
        // 创建 create 方法
        methodVisitor = classWriter.visitMethod(
            Opcodes.ACC_PUBLIC,
            Constant.METHOD_CREATE,
            "()${ObjectFormatUtil.nameToDesc(serviceImpl)}",
            null,
            null
        )
        methodVisitor.visitTypeInsn(Opcodes.NEW, ObjectFormatUtil.nameToAsmName(serviceImpl))
        methodVisitor.visitInsn(Opcodes.DUP)
        methodVisitor.visitMethodInsn(
            Opcodes.INVOKESPECIAL,
            ObjectFormatUtil.nameToAsmName(serviceImpl),
            "<init>",
            "()V",
            false
        )
        methodVisitor.visitInsn(Opcodes.ARETURN)
        methodVisitor.visitEnd()
        // 创建 synthetic 的 create 方法，不然会报 AbstractMethodError
        methodVisitor = classWriter.visitMethod(
            Opcodes.ACC_PUBLIC or Opcodes.ACC_BRIDGE or Opcodes.ACC_SYNTHETIC,
            Constant.METHOD_CREATE,
            "()${ObjectFormatUtil.nameToDesc(Constant.Class.I_SERVICE)}",
            null,
            null
        )
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
        methodVisitor.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            ObjectFormatUtil.nameToAsmName(providerClassName),
            Constant.METHOD_CREATE,
            "()${ObjectFormatUtil.nameToDesc(serviceImpl)}",
            false
        )
        methodVisitor.visitInsn(Opcodes.ARETURN)
        methodVisitor.visitEnd()

        classWriter.visitEnd()
        // 输出文件
        val providerClassPath = ObjectFormatUtil.nameToPath(providerClassName)
        FileUtils.writeByteArrayToFile(File(output, providerClassPath), classWriter.toByteArray())
    }

    /**
     * 获取ServiceProvider的类签名
     *
     * @param serviceImpl Service 实现类类名
     * @param scope Scope
     * @return
     */
    private fun getServiceProviderClassSignature(serviceImpl: String, scope: Scope): String {
        val superClassName = getServiceProviderSuperClassName(scope)
        val superClassAsmName = ObjectFormatUtil.nameToAsmName(superClassName)
        val implClassDesc = ObjectFormatUtil.nameToDesc(serviceImpl)
        return "L$superClassAsmName<$implClassDesc>;"
    }

    /**
     * 根据Scope 获取模板父类的名字
     *
     * @param scope Scope
     * @return
     */
    private fun getServiceProviderSuperClassName(scope: Scope): String {
        return when (scope) {
            Scope.ONCE ->
                Constant.Class.ONCE_PROVIDER
            Scope.SINGLETON ->
                Constant.Class.SINGLETON_PROVIDER
            else ->
                throw IllegalStateException("未知 Scope 类型")
        }
    }

    /**
     * 获取ServiceProvider的类名
     *
     * @param service Service 类名
     * @return
     */
    private fun getServiceProviderClassName(service: String): String {
        return "$service${Constant.PROVIDER_SUFFIX}"
    }
}