package win.techflowing.android.ipc.parameter

/**
 * In、Out、InOut 参数包装器
 *
 * @author techflowing@gmail.com
 * @since 2022/10/26 23:08
 */
class DirectionParameterHandler<T>(var paramType: Class<*>) : ParameterHandler<T> {

    override fun wrapper(index: Int, value: T, receiver: (index: Int, wrapper: BaseParameterWrapper) -> Unit) {
        TODO("Not yet implemented")
    }
}