package win.techflowing.android.ipc.parameter

import win.techflowing.android.ipc.annotation.In
import win.techflowing.android.ipc.annotation.InOut
import win.techflowing.android.ipc.annotation.Out
import win.techflowing.android.ipc.parameter.wrapper.BaseParameterWrapper
import win.techflowing.android.ipc.parameter.wrapper.InOutParameterWrapper
import win.techflowing.android.ipc.parameter.wrapper.InParameterWrapper
import win.techflowing.android.ipc.parameter.wrapper.OutParameterWrapper
import win.techflowing.android.ipc.util.TypeUtil
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

/**
 * In、Out、InOut 参数包装器
 *
 * @author techflowing@gmail.com
 * @since 2022/10/26 23:08
 */
class DirectionParameterHandler(
    private val annotation: Annotation,
    private val paramType: Class<*>
) : ParameterHandler {

    @Suppress("UNCHECKED_CAST")
    override fun <W : BaseParameterWrapper> wrapper(index: Int, value: Any?): W {
        if (TypeUtil.canOnlyBeInType(paramType) and (annotation !is In)) {
            throw IllegalArgumentException("Primitives are in by default, and cannot be otherwise")
        }
        if (annotation is In) {
            return InParameterWrapper(value, paramType) as W
        }
        if (annotation is Out) {
            return OutParameterWrapper(value, paramType) as W
        }
        if (annotation is InOut) {
            return InOutParameterWrapper(value, paramType) as W
        }
        throw IllegalStateException("not supported annotation type with parameter")
    }
}