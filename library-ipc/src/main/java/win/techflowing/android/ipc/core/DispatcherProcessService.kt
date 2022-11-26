package win.techflowing.android.ipc.core

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.Process
import win.techflowing.android.ipc.IServiceManager
import win.techflowing.android.util.AndroidComponentUtil
import win.techflowing.android.util.ProcessUtil

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
                registerServiceDispatcher(intent)
            }
            ACTION_REGISTER_REMOTE_SERVICE -> {
                registerRemoteService(intent)
            }
            ACTION_UNREGISTER_REMOTE_SERVICE -> {
                unregisterRemoteService(intent)
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
        val binderWrapper = intent.getParcelableExtra<BinderWrapper>(KEY_REMOTE_SERVICE_MANAGER_BINDER_WRAPPER)
        val serviceManagerBinder = binderWrapper?.getBinder() ?: return
        val pid = intent.getIntExtra(KEY_PID, -1)
        if (pid > 0) {
            IServiceManager.Stub.asInterface(serviceManagerBinder)
                ?.registerServiceDispatcher(ServiceDispatcher.getInstance().asBinder())
        }
    }

    /**
     * 注册远程 Service
     *
     * @param intent Intent
     */
    private fun registerRemoteService(intent: Intent) {
        val serviceName = intent.getStringExtra(KEY_SERVICE_CANONICAL_NAME) ?: return
        val serviceManagerBinder =
            intent.getParcelableExtra<BinderWrapper>(KEY_REMOTE_SERVICE_MANAGER_BINDER_WRAPPER)?.getBinder() ?: return
        val transporterBinder =
            intent.getParcelableExtra<BinderWrapper>(KEY_REMOTE_TRANSPORTER_BINDER_WRAPPER)?.getBinder() ?: return
        val processName = intent.getStringExtra(KEY_PROCESS_NAME) ?: return
        val pid = intent.getIntExtra(KEY_PID, -1)
        if (pid > 0) {
            ServiceDispatcher.getInstance().registerService(serviceName, pid, processName, transporterBinder)
            ServiceDispatcher.getInstance().registerServiceManager(pid, serviceManagerBinder)
        }
    }

    /**
     * 反注册 Service
     *
     * @param intent Intent
     */
    private fun unregisterRemoteService(intent: Intent) {
        val serviceName = intent.getStringExtra(KEY_SERVICE_CANONICAL_NAME) ?: return
        ServiceDispatcher.getInstance().unregisterService(serviceName)
    }

    companion object {

        private const val ACTION_REGISTER_SERVICE_DISPATCHER = "win.techflowing.android.ipc.RegisterServiceDispatcher"
        private const val ACTION_REGISTER_REMOTE_SERVICE = "win.techflowing.android.ipc.RegisterRemoteService"
        private const val ACTION_UNREGISTER_REMOTE_SERVICE = "win.techflowing.android.ipc.UnRegisterRemoteService"
        private const val KEY_REMOTE_SERVICE_MANAGER_BINDER_WRAPPER = "key_remote_service_manager_binder_wrapper"
        private const val KEY_REMOTE_TRANSPORTER_BINDER_WRAPPER = "key_remote_transporter_binder_wrapper"
        private const val KEY_PID = "key_pid"
        private const val KEY_PROCESS_NAME = "key_process_name"
        private const val KEY_SERVICE_CANONICAL_NAME = "key_service_canonical_name"

        /**
         * 通知 Dispatcher 进程向本进程注册 Dispatcher Binder 对象
         *
         * @param context Context
         * @param serviceManagerBinder 调用者当前进程的 ServiceManager Binder 对象
         */
        fun notifyRegisterServiceDispatcher(context: Context, serviceManagerBinder: IBinder) {
            val intent = Intent(context, DispatcherProcessService::class.java)
            intent.action = ACTION_REGISTER_SERVICE_DISPATCHER
            intent.putExtra(KEY_REMOTE_SERVICE_MANAGER_BINDER_WRAPPER, BinderWrapper(serviceManagerBinder))
            intent.putExtra(KEY_PID, Process.myPid())
            intent.putExtra(KEY_PROCESS_NAME, ProcessUtil.getCurrentProcessName(context))
            AndroidComponentUtil.safeStartService(context, intent)
        }

        /**
         * 使用 startService 的方式注册 Service
         *
         * @param context Context
         * @param serviceName Service 名称
         * @param serviceManagerBinder 调用者当前进程的 ServiceManager Binder 对象
         * @param transporterBinder 支持跨进程服务调用的 Binder 对象
         */
        fun registerRemoteService(
            context: Context,
            serviceName: String,
            serviceManagerBinder: IBinder,
            transporterBinder: IBinder
        ) {
            val intent = Intent(context, DispatcherProcessService::class.java)
            intent.action = ACTION_REGISTER_REMOTE_SERVICE
            intent.putExtra(KEY_SERVICE_CANONICAL_NAME, serviceName)
            intent.putExtra(KEY_REMOTE_SERVICE_MANAGER_BINDER_WRAPPER, BinderWrapper(serviceManagerBinder))
            intent.putExtra(KEY_REMOTE_TRANSPORTER_BINDER_WRAPPER, BinderWrapper(transporterBinder))
            intent.putExtra(KEY_PID, Process.myPid())
            intent.putExtra(KEY_PROCESS_NAME, ProcessUtil.getCurrentProcessName(context))
            AndroidComponentUtil.safeStartService(context, intent)
        }

        /**
         * 使用 startService 的方式反注册 Service
         *
         * @param context Context
         * @param serviceName Service 名称
         */
        fun unregisterRemoteService(
            context: Context,
            serviceName: String
        ) {
            val intent = Intent(context, DispatcherProcessService::class.java)
            intent.action = ACTION_UNREGISTER_REMOTE_SERVICE
            intent.putExtra(KEY_SERVICE_CANONICAL_NAME, serviceName)
            AndroidComponentUtil.safeStartService(context, intent)
        }
    }
}