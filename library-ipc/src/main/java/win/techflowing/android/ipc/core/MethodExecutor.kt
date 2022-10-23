package win.techflowing.android.ipc.core

import java.lang.reflect.Method

/**
 * 方法执行器，通过反射调用执行对象的方法
 *
 * @property target 当前方法所属对象
 * @property method Method，反射执行使用
 * @author techflowing@gmail.com
 * @since 2022/10/17 23:58
 */
class MethodExecutor(private val target: Any, private val method: Method) {

}