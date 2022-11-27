package win.techflowing.android.ipc.method

import win.techflowing.android.ipc.call.Call
import win.techflowing.android.ipc.call.Response
import win.techflowing.android.ipc.call.StatusCode
import java.lang.reflect.InvocationTargetException
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

    fun execute(args: Array<Any?>?): Response {
        var code = StatusCode.SUCCESS
        var message = "Call method '${method.name}' successfully!"
        val throwable: Throwable?
        try {
            val result = if (args == null) {
                method.invoke(target)
            } else {
                method.invoke(target, *args)
            }
            if (result is Call<*>) {
                return Response(code, message, result.execute())
            }
            return Response(code, message, result)
        } catch (e: IllegalAccessException) {
            code = StatusCode.BAD_REQUEST
            throwable = e
        } catch (e: InvocationTargetException) {
            code = StatusCode.INVOCATION_FAIL
            throwable = e
        } catch (e: Throwable) {
            code = StatusCode.BAD_REQUEST
            throwable = e
        }
        message = "Exception occur when execute method: ${method.name} \n ${throwable?.message}"
        return Response(code, message)
    }
}