package win.techflowing.android.app.ipc.banana;

import android.os.Bundle
import android.widget.Button

import win.techflowing.android.app.ipc.R;
import win.techflowing.android.base.BaseActivity;
import win.techflowing.android.ipc.Tartarus

/**
 * 运行于 Banana 进程的 Activity
 *
 * @author techflowing@gmail.com
 * @since 2022/10/16 22:53
 */
class BananaProcessActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipc_banana_process)

        findViewById<Button>(R.id.test_register_service).setOnClickListener {
            Tartarus.registerRemoteService(BananaService::class.java, BananaServiceImpl())
        }
    }
}
