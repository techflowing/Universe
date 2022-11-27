package win.techflowing.android.ipc.call.adapter

import win.techflowing.android.ipc.call.Call
import win.techflowing.android.ipc.call.Callback
import java.lang.IllegalStateException

/**
 * 适配返回结果
 * 此处实现方案有待考虑，怎么适配 RemoteService 方法在返回结果是 Call 时，请求端和服务端差异的问题
 *
 * @author techflowing@gmail.com
 * @since 2022/11/28 00:01
 */
class CallWrapper<T>(private val result: T?) : Call<T> {

    override fun execute(): T? {
        return result
    }

    override fun enqueue(callback: Callback<T>) {
        throw IllegalStateException("Unreachable")
    }

    override fun isExecuted(): Boolean {
        throw IllegalStateException("Unreachable")
    }

    override fun cancel() {
        throw IllegalStateException("Unreachable")
    }

    override fun isCanceled(): Boolean {
        throw IllegalStateException("Unreachable")
    }
}