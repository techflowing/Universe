package win.techflowing.android.ipc.call.adapter

import win.techflowing.android.ipc.call.Call


/**
 * Adapts a [Call] with response type `R` into the type of `T`.
 * Instances are created by [CallAdapterFactory]
 *
 * @author techflowing@gmail.com
 * @since 2022/11/1 23:11
 */
interface CallAdapter<R> {

    /**
     * Returns an instance of `T` which delegates to `call`.
     */
    fun adapt(call: Call<*>): R?
}