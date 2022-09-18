package win.techflowing.android.ipc

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.Process
import win.techflowing.android.util.AndroidComponentUtil

/**
 * 运行于 Dispatcher 进程的 Service
 *
 * @author techflowing@gmail.com
 * @since 2022/9/18 22:35
 */
class DispatcherProcessService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_REGISTER_SERVICE_DISPATCHER -> {

            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 注册 Dispatcher Binder 对象到调用者的进程
     *
     * @param intent Intent
     */
    private fun registerServiceDispatcher(intent: Intent) {
        val binderWrapper = intent.getParcelableExtra<BinderWrapper>(KEY_REMOTE_TRANSFER_BINDER_WRAPPER)

    }

    companion object {

        private const val ACTION_REGISTER_SERVICE_DISPATCHER = ""
        private const val KEY_REMOTE_TRANSFER_BINDER_WRAPPER = "key_remote_transfer_binder_wrapper"
        private const val KEY_PID = "key_pid"

        /**
         * 通知 Dispatcher 进程向本进程注册 Dispatcher Binder 对象
         *
         * @param context Context
         * @param binder 调用者当前进程的 ServiceManager Binder 对象
         */
        fun notifyRegisterServiceDispatcher(context: Context, binder: IBinder) {
            val wrapper = BinderWrapper(binder)
            val intent = Intent(context, DispatcherProcessService::class.java)
            intent.action = ACTION_REGISTER_SERVICE_DISPATCHER
            intent.putExtra(KEY_REMOTE_TRANSFER_BINDER_WRAPPER, wrapper)
            intent.putExtra(KEY_PID, Process.myPid())
            AndroidComponentUtil.safeStartService(context, intent)
        }
    }
}