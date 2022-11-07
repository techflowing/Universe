package win.techflowing.android.ipc.call

/**
 * 方法执行结果回调，使用 Call#enqueue(Callback)
 *
 * @author techflowing@gmail.com
 * @since 2022/10/30 22:04
 */
interface Callback<T> {

    /**
     * 执行成功的回调
     *
     * @param call 请求 Call
     * @param response 返回的数据
     */
    fun onResponse(call: Call<T>, response: T?)

    /**
     * 执行失败的回调
     *
     * @param call 请求 Call
     * @param t 异常
     */
    fun onFailure(call: Call<T>, t: Throwable)
}