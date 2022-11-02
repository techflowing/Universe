package win.techflowing.android.ipc.util

import java.lang.reflect.*
import java.lang.reflect.Array
import kotlin.Any
import kotlin.Boolean

/**
 * 类型相关工具类
 *
 * @author techflowing@gmail.com
 * @since 2022/10/26 21:42
 */
object TypeUtil {

    /**
     * 返回参数类型是否是支持的
     *
     * @param type 参数类型
     * @return
     */
    fun isSupportedReturnType(type: Type?): Boolean {
        return isSupportedParamType(type)
    }

    /**
     * 参数类型是否是支持的，不支持的类型：
     *
     * @param type 参数类型
     * @return
     */
    fun isSupportedParamType(type: Type?): Boolean {
        if (type is Class<*>) {
            return true
        }
        // 泛型类型
        if (type is ParameterizedType) {
            for (typeArgument in type.actualTypeArguments) {
                if (!isSupportedParamType(typeArgument)) {
                    return false
                }
            }
            return true
        }
        // 泛型数组
        if (type is GenericArrayType) {
            return isSupportedParamType(type.genericComponentType)
        }
        // 类型变量, 专指泛型中参数类型
        if (type is TypeVariable<*>) {
            return false
        }
        // 通配符类型
        if (type is WildcardType) {
            return false
        }
        return false
    }

    /**
     * 获取参数的
     *
     * @param type
     * @return
     */
    fun getRawClass(type: Type): Class<*>? {
        if (type is Class<*>) {
            // Type is a normal class.
            return type
        }
        if (type is ParameterizedType) {
            // I'm not exactly sure why getRawType() returns Type instead of Class. Neal isn't either but
            // suspects some pathological case related to nested classes exists.
            val rawType = type.rawType
            return if (rawType is Class<*>) {
                rawType
            } else {
                null
            }
        }
        if (type is GenericArrayType) {
            val componentType = type.genericComponentType
            return Array.newInstance(getRawClass(componentType)!!, 0).javaClass
        }
        if (type is TypeVariable<*>) {
            // We could use the variable's bounds, but that won't work if there are multiple. Having a raw
            // type that's more general than necessary is okay.
            return Any::class.java
        }
        if (type is WildcardType) {
            return getRawClass(type.upperBounds[0])
        }
        return null
    }
}