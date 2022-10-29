package win.techflowing.android.ipc.parameter

/**
 * 参数包装器，包装参数为 TypeWrapper 对象，包含参数类型，和参数值
 *
 * @author techflowing@gmail.com
 * @since 2022/10/26 22:04
 */
interface ParameterHandler<T> {

    /**
     * 包装参数
     *
     * @param receiver 参数包装接收器
     * @param value 参数值
     * @param index 参数在方法中的位置
     */
    fun wrapper(index: Int, value: T, receiver: (index: Int, wrapper: BaseParameterWrapper) -> Unit)
}