package win.techflowing.android.ipc.call

import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

/**
 * 异步请求线程池
 *
 * @author techflowing@gmail.com
 * @since 2022/11/5 23:46
 */
object AsyncCallExecutor {

    private const val THREAD_NAME = "Dispatcher Thread #"
    private const val KEEP_ALIVE_TIME_SECONDS = 60L
    private val threadCount = AtomicInteger(1)

    private val executorService by lazy {
        ThreadPoolExecutor(
            0, Int.MAX_VALUE, KEEP_ALIVE_TIME_SECONDS, TimeUnit.SECONDS,
            SynchronousQueue(), ThreadFactory { runnable ->
                val thread = Thread(runnable, THREAD_NAME + threadCount.getAndIncrement())
                thread.isDaemon = true
                return@ThreadFactory thread
            }
        )
    }

    fun enqueue(task: Runnable) {
        executorService.execute(task)
    }
}