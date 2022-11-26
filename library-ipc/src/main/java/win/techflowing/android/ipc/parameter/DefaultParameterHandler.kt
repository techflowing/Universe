package win.techflowing.android.ipc.parameter

import win.techflowing.android.ipc.parameter.wrapper.BaseParameterWrapper
import win.techflowing.android.ipc.parameter.wrapper.InParameterWrapper
import win.techflowing.android.ipc.util.TypeUtil

/**
 * 普通参数包装器
 *
 * @author techflowing@gmail.com
 * @since 2022/10/26 23:09
 */
class DefaultParameterHandler(var paramType: Class<*>) : ParameterHandler {

    override fun <W : BaseParameterWrapper> wrapper(index: Int, value: Any?): W {
        if (TypeUtil.canOnlyBeInType(paramType)) {
            return InParameterWrapper(value, paramType) as W
        } else {
            throw IllegalArgumentException(
                "Parameter type '${paramType.canonicalName}' can be an out type, so you must declare it as @In, @Out or @Inout."
            )
        }
    }
}