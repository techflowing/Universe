package win.techflowing.android.ipc.parameter

/**
 * In、Out、InOut 参数包装器
 *
 * @author techflowing@gmail.com
 * @since 2022/10/26 23:08
 */
class DirectionParameterHandler(var paramType: Class<*>) : ParameterHandler {
    override fun <W : BaseParameterWrapper> wrapper(index: Int, value: Any?): W {
        TODO("Not yet implemented")
    }

}