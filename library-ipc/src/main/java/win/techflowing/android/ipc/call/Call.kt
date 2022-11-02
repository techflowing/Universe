package win.techflowing.android.ipc.call

/**
 * 方法调用的抽象接口，参考 Retrofit
 *
 * @property T 返回数据的类型
 * @author techflowing@gmail.com
 * @since 2022/10/30 22:02
 */
interface Call<T> {

    /**
     * 执行请求
     *
     * @return 请求结果
     */
    fun execute(): T?

    /**
     * 异步执行请求
     *
     * @param callback 请求结果回调
     */
    fun enqueue(callback: Callback<T>)

    /**
     * 请求是否在执行
     */
    fun isExecuted(): Boolean

    /**
     * 取消请求
     */
    fun cancel()

    /**
     * 是否已经调用了请求
     */
    fun isCanceled()
}