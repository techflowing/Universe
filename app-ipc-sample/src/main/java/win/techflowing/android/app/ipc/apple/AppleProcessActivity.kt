package win.techflowing.android.app.ipc.apple;


import android.os.Bundle
import android.widget.Button

import win.techflowing.android.app.ipc.R;
import win.techflowing.android.base.BaseActivity;
import win.techflowing.android.ipc.Tartarus

/**
 * 运行于 Apple 进程的 Activity
 *
 * @author techflowing@gmail.com
 * @since 2022/10/16 22:53
 */
class AppleProcessActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipc_apple_process)

        findViewById<Button>(R.id.test_register_service).setOnClickListener {
            Tartarus.registerRemoteService(AppleService::class.java, AppleServiceImpl())
        }
    }
}
