package win.techflowing.android.ipc.core

import android.os.Binder
import android.os.RemoteCallbackList
import win.techflowing.android.ipc.IRemoteCallback
import win.techflowing.android.ipc.IRemoteService
import win.techflowing.android.ipc.aidl.ICallback
import win.techflowing.android.ipc.aidl.ITransporter
import win.techflowing.android.ipc.annotation.RemoteCallback
import win.techflowing.android.ipc.call.*
import win.techflowing.android.ipc.log.Logger
import win.techflowing.android.ipc.method.MethodExecutor
import win.techflowing.android.ipc.method.MethodRequester
import win.techflowing.android.ipc.parameter.type.TypeTable
import win.techflowing.android.ipc.parameter.wrapper.BaseParameterWrapper
import win.techflowing.android.ipc.parameter.wrapper.CallbackParameterWrapper
import win.techflowing.android.ipc.parameter.wrapper.InParameterWrapper
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy.newProxyInstance
import java.util.concurrent.atomic.AtomicInteger

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

    /** 存储 Callback name 和 Class<*> 的映射关系 */
    private val callbackClassMap = mutableMapOf<String, Class<*>>()

    /** 存储 Callback Binder, 这个 Binder 主要用于传递 Callback 的跨进程请求 */
    private val callbackList = RemoteCallbackList<ICallback>()

    /** 单次请求ID */
    private val requestId = AtomicInteger(0)

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
                val previous = methodExecutorMap.put(method.name, MethodExecutor(serviceImpl, method))
                if (previous != null) {
                    throw IllegalStateException("not allowed same function name")
                }
                registerCallbackClassIfNeed(method)
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
    fun invokeRemoteServiceMethod(
        transporter: ITransporter,
        method: Method,
        args: Array<Any?>?
    ): Any? {
        val methodRequester = generateServiceRequester(method)
        val requestId = requestId.getAndIncrement()
        val remoteServiceCall = RemoteServiceCall(transporter, methodRequester, args, requestId)
        CallbackTransporter.get().registerCallbackArgsIfNeed(requestId, method, args)
        return methodRequester.getCallAdapter().adapt(remoteServiceCall)
    }

    override fun execute(request: Request?): Response {
        request?.also {
            Logger.i(
                TAG,
                "receive request, target class: ${request.getTargetClass()}, method: ${request.getMethodName()}"
            )
            return invokeMethod(request)
        }
        return Response(StatusCode.BAD_REQUEST, "request info is null")
    }

    override fun registerCallback(callback: ICallback?) {
        val pid = Binder.getCallingPid()
        Logger.d(TAG, "register callback:$callback , pid: $pid")
        callback?.also {
            callbackList.register(it, pid)
        }
    }

    override fun unregisterCallback(callback: ICallback?) {
        val pid = Binder.getCallingPid()
        Logger.d(TAG, "unregister callback:$callback , pid: $pid")
        callback?.also {
            callbackList.unregister(callback)
        }
    }

    /**
     * 注册 Callback 参数的 Class 类型记录
     *
     * @param method 方法 Method
     */
    private fun registerCallbackClassIfNeed(method: Method) {
        val parameterTypes = method.parameterTypes
        val parameterAnnotations = method.parameterAnnotations
        for (index in parameterTypes.indices) {
            val annotations = parameterAnnotations[index] ?: continue
            for (annotation in annotations) {
                if (annotation is RemoteCallback) {
                    val paramType = parameterTypes[index]
                    if (!IRemoteCallback::class.java.isAssignableFrom(paramType)) {
                        throw IllegalArgumentException("callback parameter must implementation IRemoteCallback")
                    }
                    callbackClassMap[paramType.simpleName] = paramType
                    break
                }
            }
        }
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

    /**
     * 反射调用目标类的方法
     *
     * @param request 请求信息
     */
    private fun invokeMethod(request: Request): Response {
        var params: Array<Any?>? = null
        request.getParameterWrapper()?.also { wrapperArray ->
            params = Array(wrapperArray.size) { index ->
                if (wrapperArray[index].getType() == TypeTable.CALLBACK.ordinal) {
                    val pid = Binder.getCallingPid()
                    val callbackParamWrapper = wrapperArray[index] as CallbackParameterWrapper
                    val callbackClassName = callbackParamWrapper.getCallbackClassName()
                    val callbackClass = callbackClassMap[callbackClassName]
                        ?: throw IllegalStateException("can't find callback class：$callbackClassName")
                    createCallbackProxyInstance(request.getId(), callbackClass, index, pid)
                } else {
                    wrapperArray[index].getParam()
                }
            }
        }
        val methodExecutor = serviceMethodExecutorMap[request.getTargetClass()]?.get(request.getMethodName())
            ?: return Response(StatusCode.NOT_FOUND, "The method '${request.getMethodName()}' you call was not exist!")
        return methodExecutor.execute(params)
    }

    /**
     * 创建 Callback 参数的动态代理类
     *
     * @param T Callback 参数类型
     * @param index Callback 参数在方法请求中的参数 Index 位置
     * @return
     */
    private fun <T> createCallbackProxyInstance(requestId: Int, callbackClass: Class<*>, index: Int, pid: Int): T {
        if (!IRemoteCallback::class.java.isAssignableFrom(callbackClass)) {
            throw IllegalArgumentException("${callbackClass.name} must implementation IRemoteCallback")
        }
        return newProxyInstance(callbackClass.classLoader, arrayOf(callbackClass), object : InvocationHandler {
            override fun invoke(proxy: Any, method: Method, args: Array<Any?>?): Any? {
                if (method.declaringClass == Object::class.java) {
                    return if (args != null) method.invoke(this, *args) else method.invoke(this)
                }
                return invokeRemoteCallbackMethod(requestId, callbackClass, index, method, args, pid)
            }
        }) as T
    }

    /**
     * 调用 Callback 参数的类方法，通过 ICallback Binder 跨进程调用
     *
     * @return
     */
    private fun invokeRemoteCallbackMethod(
        requestId: Int,
        callbackClass: Class<*>,
        callbackParamIndex: Int,
        method: Method,
        args: Array<Any?>?,
        pid: Int
    ): Any? {
        var result: Any? = null
        val size = callbackList.beginBroadcast()
        for (index in 0 until size) {
            val cookieId = callbackList.getBroadcastCookie(index)
            if (cookieId == pid) {
                try {
                    val request = createCallbackRequest(
                        requestId,
                        callbackClass.simpleName,
                        callbackParamIndex,
                        method.name,
                        args
                    )
                    val response = callbackList.getBroadcastItem(index).callback(request)
                    result = response.getResult()
                    if (response.getStatusCode() != StatusCode.SUCCESS) {
                        Logger.e(TAG, "Execute remote callback fail: $response")
                    }
                } catch (e: Exception) {
                    Logger.e(TAG, "Error when execute callback!, message: ${e.message}")
                    callbackList.finishBroadcast()
                }
                break
            }
        }
        callbackList.finishBroadcast()
        return result
    }

    /**
     * 创建 Callback 远程请求的 Request
     *
     * @param targetClass 目标 Callback 的类名
     * @param methodName 目标 Callback 的方法名
     * @param callbackParamIndex @link [CallbackParameterWrapper] 处理一个方法有多个相同 Callback 参数的场景
     * @param args 参数
     * @return
     */
    private fun createCallbackRequest(
        requestId: Int,
        targetClass: String,
        callbackParamIndex: Int,
        methodName: String,
        args: Array<Any?>?
    ): CallbackRequest {
        val argsLength = args?.size ?: 0
        val paramsWrapper = Array<BaseParameterWrapper>(argsLength) { index ->
            val paramClass = args?.get(index)?.javaClass
                ?: throw IllegalArgumentException("callback args can't null")
            InParameterWrapper(args[index], paramClass)
        }
        return CallbackRequest(requestId, targetClass, callbackParamIndex, methodName, paramsWrapper)
    }

    companion object {

        const val TAG = "Transporter"

        private val ins by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Transporter()
        }

        fun getInstance(): Transporter {
            return ins
        }
    }
}