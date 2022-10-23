package win.techflowing.android.ipc.core

import win.techflowing.android.ipc.IRemoteService
import win.techflowing.android.ipc.ITransporter
import win.techflowing.android.ipc.Tartarus
import win.techflowing.android.ipc.log.Logger
import win.techflowing.android.util.ProcessUtil

/**
 * 跨进程方法调用 Binder，运行于每一个进程内
 *
 * @author techflowing@gmail.com
 * @since 2022/9/28 23:34
 */
class Transporter private constructor() : ITransporter.Stub() {

    /** 方法执行器映射表，二级映射，类名-方法名 */
    private val serviceMethodExecutorMap = mutableMapOf<String, MutableMap<String, MethodExecutor>>()

    /**
     * 注册Service，创建 Method 执行映射表
     *
     * @param service Service Class
     * @param serviceImpl Service 实现对象
     */
    fun <SERVICE : IRemoteService, IMPL : SERVICE> registerService(service: Class<SERVICE>, serviceImpl: IMPL) {
        service.canonicalName?.also { serviceName ->
            val methodExecutorMap = mutableMapOf<String, MethodExecutor>()
            service.declaredMethods.forEach { method ->
                if (method.isBridge) {
                    return
                }
                methodExecutorMap[method.name] = MethodExecutor(serviceImpl, method)
            }
            serviceMethodExecutorMap[serviceName] = methodExecutorMap
        }
    }

    override fun basicTypes() {
        Logger.e("yuanzeng", "进程名字：" + ProcessUtil.getCurrentProcessName(Tartarus.getContext()))
    }

    companion object {

        private val ins by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Transporter()
        }

        fun getInstance(): Transporter {
            return ins
        }
    }
}