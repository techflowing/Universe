package win.techflowing.android.plugin.service

import win.techflowing.service.annotation.ServiceImpl
import win.techflowing.service.manager.IService

/**
 * 常量定义
 *
 * @author techflowing@gmail.com
 * @since 2022/6/4 11:42 下午
 */
object Constant {

    /** 注解 */
    object Annotation {
        /** ServiceImpl 类注解 */
        val SERVICE_IMPL: String = ServiceImpl::class.qualifiedName!!
    }

    /** 类信息 */
    object Class {
        val I_SERVICE: String = IService::class.qualifiedName!!
    }
}