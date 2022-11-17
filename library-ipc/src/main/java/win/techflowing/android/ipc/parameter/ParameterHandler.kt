package win.techflowing.android.ipc.parameter

import win.techflowing.android.ipc.parameter.wrapper.BaseParameterWrapper

/**
 * 参数包装器，包装参数为 TypeWrapper 对象，包含参数类型，和参数值
 *
 * @author techflowing@gmail.com
 * @since 2022/10/26 22:04
 */
interface ParameterHandler {

    /**
     * 包装参数
     *
     * @param value 参数值
     * @param index 参数在方法中的位置
     * @param W 子类
     */
    fun <W : BaseParameterWrapper> wrapper(index: Int, value: Any?): W
}