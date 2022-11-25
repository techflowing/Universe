package win.techflowing.android.ipc.call

import android.os.RemoteException
import win.techflowing.android.ipc.aidl.ITransporter
import win.techflowing.android.ipc.log.Logger
import win.techflowing.android.ipc.method.MethodRequester
import win.techflowing.android.ipc.parameter.wrapper.BaseParameterWrapper

/**
 * 远程方法调用封装
 *
 * @author techflowing@gmail.com
 * @since 2022/10/30 22:09
 */
class RemoteServiceCall(
    private val transporter: ITransporter,
    private val methodRequester: MethodRequester,
    private val args: Array<Any?>?,
    private val id: Int
) : Call<Any> {

    @Volatile
    private var executed = false

    @Volatile
    private var canceled = false

    override fun execute(): Any? {
        checkExecuted()
        if (canceled) {
            Logger.w(TAG, "RemoteServiceCall already canceled")
            return null
        }
        var result: Any? = null
        try {
            result = executeInternal()
        } catch (e: RemoteException) {
            Logger.e(TAG, e.message ?: "execute exception")
            e.printStackTrace()
        }
        return result
    }

    override fun enqueue(callback: Callback<Any>) {
        checkExecuted()
        if (canceled) {
            Logger.w(TAG, "RemoteServiceCall already canceled")
            callback.onFailure(this, IllegalStateException("already canceled"))
            return
        }
        AsyncCallExecutor.enqueue(AsyncCall(this, callback) {
            executeInternal()
        })
    }

    override fun isExecuted(): Boolean {
        return executed
    }

    override fun cancel() {
        canceled = true
    }

    override fun isCanceled(): Boolean {
        return canceled
    }

    /**
     * 真正发起请求的逻辑
     *
     * @return
     */
    @Throws(RemoteException::class)
    private fun executeInternal(): Any? {
        val paramsHandlers = methodRequester.getParameterHandlers()
        val argsCount = args?.size ?: 0
        if (argsCount != paramsHandlers.size) {
            throw IllegalArgumentException(
                "Argument count ($argsCount) doesn't match expected parameter handler count (${paramsHandlers.size})"
            )
        }
        val paramsWrapper = Array<BaseParameterWrapper>(argsCount) { index ->
            return@Array paramsHandlers[index].wrapper(index, args?.get(index))
        }
        val request = Request(
            id,
            methodRequester.getClassName(),
            methodRequester.getMethodName(),
            paramsWrapper,
            methodRequester.isOneWay()
        )
        val response = transporter.execute(request) ?: return null
        if (response.getStatusCode() != StatusCode.SUCCESS) {
            Logger.e(TAG, "execute remote service call fail: ${response.getStatusMessage()}")
        }
        return response.getResult()
    }

    private fun checkExecuted() {
        synchronized(this) {
            if (executed) {
                throw IllegalStateException("RemoteServiceCall already executed")
            }
            executed = true
        }
    }

    class AsyncCall(
        val call: RemoteServiceCall,
        private val callback: Callback<Any>,
        private val execute: () -> Any?
    ) : Runnable {
        override fun run() {
            try {
                val result = execute.invoke()
                callback.onResponse(call, result)
            } catch (e: RemoteException) {
                Logger.e(TAG, e.message ?: "execute exception")
                e.printStackTrace()
                callback.onFailure(call, e)
            }
        }
    }

    companion object {
        const val TAG = "RemoteServiceCall"
    }
}