package win.techflowing.android.ipc.parameter

import win.techflowing.android.ipc.parameter.wrapper.BaseParameterWrapper
import win.techflowing.android.ipc.parameter.wrapper.CallbackParameterWrapper

/**
 * Callback 类参数包装器
 *
 * @author techflowing@gmail.com
 * @since 2022/10/26 23:07
 */
class CallbackParameterHandler(private val paramType: Class<*>) : ParameterHandler {

    @Suppress("UNCHECKED_CAST")
    override fun <W : BaseParameterWrapper> wrapper(index: Int, value: Any?): W {
        return CallbackParameterWrapper(paramType.simpleName) as W
    }
}