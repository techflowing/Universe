package win.techflowing.android.ipc.method

import win.techflowing.android.ipc.Tartarus
import win.techflowing.android.ipc.annotation.*
import win.techflowing.android.ipc.call.adapter.CallAdapter
import win.techflowing.android.ipc.call.adapter.DefaultCallAdapterFactory
import win.techflowing.android.ipc.parameter.CallbackParameterHandler
import win.techflowing.android.ipc.parameter.DefaultParameterHandler
import win.techflowing.android.ipc.parameter.DirectionParameterHandler
import win.techflowing.android.ipc.parameter.ParameterHandler
import win.techflowing.android.ipc.util.TypeUtil
import java.lang.IllegalArgumentException
import java.lang.reflect.Method
import java.lang.reflect.Type
import java.util.*

/**
 * 方法请求器，包含方法的信息，参数信息，所属类信息等
 *
 * @author techflowing@gmail.com
 * @since 2022/10/24 00:15
 */
class MethodRequester private constructor(
    private val className: String,
    private val methodName: String,
    private val callAdapter: CallAdapter<*, *>,
    private val parameterHandlers: Array<ParameterHandler>,
    private val oneWay: Boolean
) {

    fun getClassName(): String {
        return className
    }

    fun getMethodName(): String {
        return methodName
    }

    fun getParameterHandlers(): Array<ParameterHandler> {
        return parameterHandlers
    }

    fun getCallAdapter(): CallAdapter<*, *> {
        return callAdapter
    }

    fun isOneWay(): Boolean {
        return oneWay
    }

    class Builder(private val method: Method) {

        fun build(): MethodRequester {
            val className = method.declaringClass.canonicalName!!
            val methodName = method.name
            val oneWay = method.annotations.any { it is OneWay }
            // 处理参数，获取所有参数的类型
            val paramTypes = method.genericParameterTypes
            // 二维数组，因为每个参数可能有多个注解
            val parameterAnnotations = method.parameterAnnotations
            val paramCount = parameterAnnotations.size
            val parameterHandlers = mutableListOf<ParameterHandler>()
            for (index in 0 until paramCount) {
                val paramType = paramTypes[index]
                if (!TypeUtil.isSupportedParamType(paramType)) {
                    throw IllegalArgumentException(
                        buildExceptionMessage("unsupported parameter type: ${paramType.javaClass.name}, index: $index")
                    )
                }
                parameterHandlers.add(index, parseParameterHandler(index, paramType, parameterAnnotations[index]))
            }
            val callAdapter = createCallAdapter()
            return MethodRequester(className, methodName, callAdapter, parameterHandlers.toTypedArray(), oneWay)
        }

        /**
         * 创建请求适配器
         */
        private fun createCallAdapter(): CallAdapter<*, *> {
            val returnType = method.genericReturnType
            if (!TypeUtil.isSupportedReturnType(returnType)) {
                throw IllegalArgumentException(
                    buildExceptionMessage("Method return type unsupported")
                )
            }
            val annotations = method.annotations
            Tartarus.getAdapterFactory().forEach { item ->
                item.get(returnType, annotations)?.also {
                    return it
                }
            }
            return DefaultCallAdapterFactory.getInstance().get(returnType, annotations)!!
        }

        /**
         * 根据参数注解获取参数包装器类型
         *
         * @param parameterType 参数类型
         * @param annotations 参数注解
         * @return
         */
        private fun parseParameterHandler(
            index: Int,
            parameterType: Type,
            annotations: Array<Annotation>?
        ): ParameterHandler {
            val rawParameterClass = TypeUtil.getRawClass(parameterType)
                ?: throw IllegalArgumentException(
                    buildExceptionMessage(
                        "get parameter raw class error，type: ${parameterType.javaClass.name}, index: $index"
                    )
                )
            if (annotations == null || annotations.isEmpty()) {
                return DefaultParameterHandler(rawParameterClass)
            }
            for (annotation in annotations) {
                if (annotation is Callback) {
                    return CallbackParameterHandler(rawParameterClass)
                } else if (annotation is In || annotation is Out || annotation is InOut) {
                    return DirectionParameterHandler(rawParameterClass)
                }
            }
            throw IllegalArgumentException(
                buildExceptionMessage("unsupported annotation found: ${Arrays.toString(annotations)}")
            )
        }

        /**
         * 包装错误信息
         *
         * @param message 错误信息
         */
        private fun buildExceptionMessage(message: String): String {
            return "$message \n    for method ${method.declaringClass.name}.${method.name}"
        }
    }
}