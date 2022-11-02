package win.techflowing.android.ipc.call

import win.techflowing.android.ipc.ITransporter
import win.techflowing.android.ipc.method.MethodRequester

/**
 * 远程方法调用封装
 *
 * @author techflowing@gmail.com
 * @since 2022/10/30 22:09
 */
class RemoteServiceCall(
    private val transporter: ITransporter,
    private val methodRequester: MethodRequester,
    private val args: Array<Any?>
) : Call<Any> {

    override fun execute(): Any {
        TODO("Not yet implemented")
    }

    override fun enqueue(callback: Callback<Any>) {
        TODO("Not yet implemented")
    }

    override fun isExecuted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun isCanceled() {
        TODO("Not yet implemented")
    }
}