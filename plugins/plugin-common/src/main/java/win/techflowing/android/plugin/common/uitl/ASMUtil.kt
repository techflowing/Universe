package win.techflowing.android.plugin.common.uitl

import org.objectweb.asm.Opcodes


/**
 * ASM 相关工具类
 *
 * @author techflowing@gmail.com
 * @since 2022/6/4 11:41 PM
 */
object ASMUtil {

    /** 判断一个类是否是接口 */
    fun isInterface(access: Int?): Boolean {
        return access != null && (access and Opcodes.ACC_INTERFACE) != 0
    }

    /** 判断一个类是否是 Public */
    fun isPublicClass(access: Int?): Boolean {
        return access != null && (access and Opcodes.ACC_PUBLIC) != 0
    }

    /** 判断一个方法是否是 public */
    fun isPublicMethod(access: Int?): Boolean {
        return access != null && (access and Opcodes.ACC_PUBLIC) != 0
    }

    /** 判断一个方法是否是实例构造方法 */
    fun isInitMethod(name: String): Boolean {
        return name == "<init>"
    }

    /** 判断一个方法是否是实例构造方法 */
    fun isStaticInitMethod(name: String): Boolean {
        return name == "<clinit>"
    }
}