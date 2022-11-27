package win.techflowing.android.ipc.call.adapter

import win.techflowing.android.ipc.call.Call
import win.techflowing.android.ipc.call.RemoteServiceCall
import win.techflowing.android.ipc.util.TypeUtil
import java.lang.reflect.Type

/**
 * 创建兜底的 CallAdapter，直接执行请求
 *
 * @author techflowing@gmail.com
 * @since 2022/11/1 23:15
 */
class DefaultCallAdapterFactory : CallAdapterFactory() {

    override fun get(returnType: Type, annotations: Array<Annotation>): CallAdapter<*> {
        return object : CallAdapter<Any> {
            override fun adapt(call: Call<Any>): Any? {
                val rawClass = TypeUtil.getRawClass(returnType)
                var result = call.execute()
                if (result == null) {
                    result = createDefaultResult(rawClass)
                }
                return result
            }
        }
    }

    private fun createDefaultResult(returnType: Class<*>?): Any? {
        when (returnType) {
            Byte::class.javaPrimitiveType -> {
                return 0.toByte()
            }
            Short::class.javaPrimitiveType -> {
                return 0.toShort()
            }
            Int::class.javaPrimitiveType,
            Long::class.javaPrimitiveType,
            Float::class.javaPrimitiveType,
            Double::class.javaPrimitiveType -> {
                return 0
            }
            Boolean::class.javaPrimitiveType -> {
                return false
            }
            Char::class.javaPrimitiveType -> {
                return ' '
            }
            else -> return null
        }
    }

    companion object {

        private val ins by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DefaultCallAdapterFactory()
        }

        fun getInstance(): DefaultCallAdapterFactory {
            return ins
        }
    }
}