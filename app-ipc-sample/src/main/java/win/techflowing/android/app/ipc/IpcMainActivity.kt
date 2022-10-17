package win.techflowing.android.app.ipc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import win.techflowing.android.app.ipc.apple.AppleProcessActivity
import win.techflowing.android.base.BaseActivity

/**
 * 首页
 *
 * @author techflowing@gmail.com @version 1.0.0
 * @since 2022/5/6 11:24 下午
 */
class IpcMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipc_main)

        findViewById<Button>(R.id.start_apple_process_activity).setOnClickListener {
            startActivity(Intent(this, AppleProcessActivity::class.java))
        }
    }
}