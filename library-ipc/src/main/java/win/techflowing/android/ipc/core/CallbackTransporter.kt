package win.techflowing.android.ipc.core

import win.techflowing.android.ipc.IRemoteCallback
import win.techflowing.android.ipc.aidl.ICallback
import win.techflowing.android.ipc.annotation.RemoteCallback
import win.techflowing.android.ipc.call.CallbackRequest
import win.techflowing.android.ipc.call.Response
import win.techflowing.android.ipc.call.StatusCode
import win.techflowing.android.ipc.log.Logger
import win.techflowing.android.ipc.method.MethodExecutor
import java.lang.reflect.Method
import java.util.concurrent.ConcurrentHashMap

/**
 * Callback 数据传输
 *
 * @author techflowing@gmail.com
 * @since 2022/11/24 00:22
 */
class CallbackTransporter private constructor() : ICallback.Stub() {

    /** Callback 执行器映射表，三级映射，Request ID - Callback 类名 - 方法名，主要在 Service 请求端使用，用户反射执行 Callback 的 Method */
    private val callbackMethodExecutorMap =
        ConcurrentHashMap<Int, ConcurrentHashMap<String, ConcurrentHashMap<String, MethodExecutor>>>()

    /**
     * 请求端调用方法是，如果有 Callback 类型参数，需要记录下
     *
     * @param requestId 单次请求的 Id 标识
     * @param method Method
     * @param args 请求方法参数
     */
    fun registerCallbackArgsIfNeed(requestId: Int, method: Method, args: Array<Any?>?) {
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
                    registerCallbackMethodExecutor(requestId, paramType, args?.get(index), index)
                    break
                }
            }
        }
    }

    /**
     * 注册 Callback 参数的回调方法执行器
     */
    private fun registerCallbackMethodExecutor(requestId: Int, callbackType: Class<*>, callbackObj: Any?, index: Int) {
        if (callbackObj == null) {
            return
        }
        val methodMap = ConcurrentHashMap<String, MethodExecutor>()
        callbackObj.javaClass.declaredMethods.forEach { method ->
            val map = callbackMethodExecutorMap.getOrPut(requestId) {
                ConcurrentHashMap()
            }
            methodMap[method.name] = MethodExecutor(callbackObj, method)
            map[callbackType.simpleName + index] = methodMap
        }
    }

    /**
     * 移除缓存的对象，避免内存泄漏
     *
     * @param request Callback 请求信息
     */
    private fun removeMethodExecutor(request: CallbackRequest) {
        // TODO: 处理未被调用的 Callback 方法的内存泄漏问题
        val callbackClassMap = callbackMethodExecutorMap[request.getId()] ?: return
        val targetCallbackClass = request.getTargetClass() + request.getCallbackParameterIndex()
        val callbackMethodMap = callbackClassMap[targetCallbackClass]
        callbackMethodMap?.remove(request.getMethodName())
        if (callbackMethodMap?.isEmpty() == true) {
            callbackClassMap.remove(targetCallbackClass)
        }
        if (callbackClassMap.isEmpty()) {
            callbackMethodExecutorMap.remove(request.getId())
        }
    }

    override fun callback(request: CallbackRequest): Response {
        Logger.d(TAG, "收到 Callback 回调方法: ${request.getTargetClass()}.${request.getMethodName()}")
        val methodExecutor =
            callbackMethodExecutorMap[request.getId()]?.get(request.getTargetClass() + request.getCallbackParameterIndex())
                ?.get(request.getMethodName())
                ?: return Response(
                    StatusCode.NOT_FOUND,
                    "The method ' ${request.getTargetClass()}.${request.getMethodName()}' you call was not exist!"
                )
        removeMethodExecutor(request)
        var params: Array<Any?>? = null
        request.getParameterWrapper()?.also { wrapperArray ->
            params = Array(wrapperArray.size) { index ->
                wrapperArray[index].getParam()
            }
        }
        return methodExecutor.execute(params)
    }

    companion object {
        const val TAG = "CallbackTransporter"
        private val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            CallbackTransporter()
        }

        fun get(): CallbackTransporter {
            return instance
        }
    }
}