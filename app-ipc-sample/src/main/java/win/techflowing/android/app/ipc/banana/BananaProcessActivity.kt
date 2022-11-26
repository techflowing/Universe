package win.techflowing.android.app.ipc.banana;

import android.os.Bundle
import android.widget.Button
import android.widget.Toast

import win.techflowing.android.app.ipc.R;
import win.techflowing.android.app.ipc.apple.AppleService
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

        findViewById<Button>(R.id.get_apple_process_service).setOnClickListener {
            Tartarus.getRemoteService(AppleService::class.java)?.also {
                Toast.makeText(this@BananaProcessActivity, "苹果进程名称：" + it.getAppleName(), Toast.LENGTH_LONG).show()
            } ?: Toast.makeText(this@BananaProcessActivity, "苹果进程服务不存在", Toast.LENGTH_LONG).show()
        }
    }
}
