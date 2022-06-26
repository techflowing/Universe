package win.techflowing.android.plugin.service

import org.objectweb.asm.Opcodes
import win.techflowing.service.manager.annotation.ServiceImpl
import win.techflowing.service.manager.IService
import win.techflowing.service.manager.ServiceManager
import win.techflowing.service.manager.provider.OnceServiceProvider
import win.techflowing.service.manager.provider.SingletonServiceProvider

/**
 * 常量定义
 *
 * @author techflowing@gmail.com
 * @since 2022/6/4 11:42 下午
 */
object Constant {

    const val ASM_VERSION = Opcodes.ASM9

    /** 生成的Service 构造类名称后缀 */
    const val PROVIDER_SUFFIX = ServiceManager.PROVIDER_SUFFIX

    /** 创建对象的方法名 */
    const val METHOD_CREATE = "create"

    /** 注解 */
    object Annotation {
        /** ServiceImpl 类注解 */
        val SERVICE_IMPL: String = ServiceImpl::class.qualifiedName!!
    }

    /** 类信息 */
    object Class {
        val I_SERVICE: String = IService::class.qualifiedName!!
        val ONCE_PROVIDER: String = OnceServiceProvider::class.qualifiedName!!
        val SINGLETON_PROVIDER: String = SingletonServiceProvider::class.qualifiedName!!
    }
}