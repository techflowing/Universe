package win.techflowing.android.ipc.core

import win.techflowing.android.ipc.IRemoteService
import win.techflowing.android.ipc.aidl.ITransporter
import win.techflowing.android.ipc.call.RemoteServiceCall
import win.techflowing.android.ipc.call.Request
import win.techflowing.android.ipc.call.Response
import win.techflowing.android.ipc.method.MethodExecutor
import win.techflowing.android.ipc.method.MethodRequester
import java.lang.reflect.Method

/**
 * 跨进程方法调用 Binder，运行于每一个进程内
 *
 * @author techflowing@gmail.com
 * @since 2022/9/28 23:34
 */
class Transporter private constructor() : ITransporter.Stub() {

    /** 方法执行器映射表，二级映射，类名-方法名，主要在 Service 服务端使用，用于真实反射执行 Method */
    private val serviceMethodExecutorMap = mutableMapOf<String, MutableMap<String, MethodExecutor>>()

    /** 方法请求器映射表，主要在 Service 请求端使用，用户包装方法参数信息 */
    private val serviceMethodRequesterMap = mutableMapOf<Method, MethodRequester>()

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

    /**
     * 调用远程服务的 方法
     *
     * @param transporter 传输 Binder 代理对象
     * @param method 远程服务的目标 Method
     * @param args 调用参数
     * @return 方法调用返回值
     */
    fun invokeRemoteServiceMethod(transporter: ITransporter, method: Method, vararg args: Any?): Any? {
        val methodRequester = generateServiceRequester(method)
        val remoteServiceCall = RemoteServiceCall(transporter, methodRequester, arrayOf(args))
        return null

    }

    /**
     * 构建方法请求器
     *
     * @param method 方法信息
     */
    private fun generateServiceRequester(method: Method): MethodRequester {
        serviceMethodRequesterMap[method]?.also {
            return it
        }
        synchronized(serviceMethodRequesterMap) {
            serviceMethodRequesterMap[method]?.also {
                return it
            }
            val requester = MethodRequester.Builder(method).build()
            serviceMethodRequesterMap[method] = requester
            return requester
        }
    }

    companion object {

        private val ins by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Transporter()
        }

        fun getInstance(): Transporter {
            return ins
        }
    }

    override fun execute(request: Request): Response {
        TODO("Not yet implemented")
    }
}