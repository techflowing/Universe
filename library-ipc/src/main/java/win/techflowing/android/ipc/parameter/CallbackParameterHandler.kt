package win.techflowing.android.ipc.parameter

/**
 * Callback 类参数包装器
 *
 * @author techflowing@gmail.com
 * @since 2022/10/26 23:07
 */
class CallbackParameterHandler(var paramType: Class<*>) : ParameterHandler {
    override fun <W : BaseParameterWrapper> wrapper(index: Int, value: Any?): W {
        TODO("Not yet implemented")
    }
}