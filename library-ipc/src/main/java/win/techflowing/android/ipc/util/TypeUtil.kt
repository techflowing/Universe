package win.techflowing.android.ipc.util

import java.lang.reflect.*
import java.lang.reflect.Array
import kotlin.Boolean
import kotlin.Byte
import kotlin.Char
import kotlin.Double
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.Short
import kotlin.String

/**
 * 类型相关工具类
 *
 * @author techflowing@gmail.com
 * @since 2022/10/26 21:42
 */
object TypeUtil {

    fun createArrayFromComponentType(componentType: String, length: Int): Any? {
        var obj: Any? = null
        try {
            val clsType = Class.forName(componentType)
            obj = Array.newInstance(clsType, length)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return obj
    }

    fun createObjFromClassName(clsName: String): Any? {
        var obj: Any? = null
        try {
            obj = Class.forName(clsName).newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return obj
    }

    /**
     * 当前参数类型是否只能使用 @in 注解
     *
     * @param classType Class
     */
    fun canOnlyBeInType(classType: Class<*>): Boolean {
        return isPrimitiveType(classType) || classType == String::class.java || classType == CharSequence::class.java
    }

    /**
     * 是否是基本类型
     *
     * @param classType Class
     */
    fun isPrimitiveType(classType: Class<*>): Boolean {
        return classType.isPrimitive || classType == Byte::class.java
                || classType == Short::class.java || classType == Int::class.java
                || classType == Long::class.java || classType == Float::class.java
                || classType == Double::class.java || classType == Boolean::class.java
                || classType == Char::class.java
    }

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