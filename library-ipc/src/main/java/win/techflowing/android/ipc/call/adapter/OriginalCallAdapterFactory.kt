package win.techflowing.android.ipc.call.adapter

import android.os.Handler
import android.os.Looper
import win.techflowing.android.ipc.call.Call
import win.techflowing.android.ipc.call.Callback
import win.techflowing.android.ipc.util.TypeUtil
import java.lang.IllegalStateException
import java.lang.reflect.Type
import java.util.concurrent.Executor

/**
 *  A [CallAdapterFactory] which uses the original [Call], just return as is.
 *
 * @author techflowing@gmail.com
 * @since 2022/11/1 23:18
 */
class OriginalCallAdapterFactory(private val callbackExecutor: Executor) : CallAdapterFactory() {

    constructor() : this(MainThreadExecutor()) {

    }

    override fun get(returnType: Type, annotations: Array<Annotation>): CallAdapter<*>? {
        if (TypeUtil.getRawClass(returnType) != Call::class.java) {
            return null
        }
        return object : CallAdapter<Call<Any>> {
            override fun adapt(call: Call<Any>): Call<Any> {
                return ExecutorCallbackCall(callbackExecutor, call)
            }
        }
    }

    class ExecutorCallbackCall<T>(
        private val callbackExecutor: Executor,
        private val delegate: Call<T>
    ) : Call<T> {
        override fun execute(): T? {
            return delegate.execute()
        }

        override fun enqueue(callback: Callback<T>) {
            delegate.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: T?) {
                    callbackExecutor.execute {
                        if (delegate.isCanceled()) {
                            callback.onFailure(this@ExecutorCallbackCall, IllegalStateException("already canceled!"))
                        } else {
                            callback.onResponse(this@ExecutorCallbackCall, response)
                        }
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    callbackExecutor.execute {
                        callback.onFailure(this@ExecutorCallbackCall, t)
                    }
                }
            })
        }

        override fun isExecuted(): Boolean {
            return delegate.isExecuted()
        }

        override fun cancel() {
            delegate.cancel()
        }

        override fun isCanceled(): Boolean {
            return delegate.isCanceled()
        }
    }

    class MainThreadExecutor : Executor {

        private val mainHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable?) {
            command?.also {
                mainHandler.post(it)
            }
        }
    }
}