package win.techflowing.android.ipc.call.adapter

import java.lang.reflect.Type

/**
 * 创建 [CallAdapter] 的工厂类
 *
 * @author techflowing@gmail.com
 * @since 2022/11/1 23:11
 */
abstract class CallAdapterFactory {

    /**
     * Returns a call adapter for interface methods that return `returnType`, or null if it
     * cannot be handled by this factory.
     */
    abstract fun get(returnType: Type, annotations: Array<Annotation>): CallAdapter<*>?
}