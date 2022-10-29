package win.techflowing.android.app.ipc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import win.techflowing.android.app.ipc.apple.AppleProcessActivity
import win.techflowing.android.app.ipc.apple.AppleService
import win.techflowing.android.app.ipc.banana.BananaProcessActivity
import win.techflowing.android.app.ipc.banana.BananaService
import win.techflowing.android.base.BaseActivity
import win.techflowing.android.ipc.Tartarus
import win.techflowing.android.log.XLog
import java.util.logging.Logger

/**
 * 首页
 *
 * @author techflowing@gmail.com @version 1.0.0
 * @since 2022/5/6 11:24 下午
 */
class IpcMainActivity : BaseActivity() {

    val TAG = "IpcMainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipc_main)

        findViewById<Button>(R.id.start_apple_process_activity).setOnClickListener {
            startActivity(Intent(this, AppleProcessActivity::class.java))
        }

        findViewById<Button>(R.id.start_banana_process_activity).setOnClickListener {
            startActivity(Intent(this, BananaProcessActivity::class.java))
        }

        findViewById<Button>(R.id.get_apple_process_service).setOnClickListener {
            Tartarus.getRemoteService(AppleService::class.java)?.also {
                XLog.e(TAG, "苹果进程服务结果：" + it.getAppleName())
                it.basicType(1, 10, 100, 1000, 1.0f, 1.0, true, 'A')
                it.arrayType(arrayOf("1", "2"), arrayOf(1, 2, 3, 4))
                it.collectType(listOf("1", "2"))
                // it.genericType(listOf("1"), "!")
                // it.genericArrayType(arrayOf("1"))
            }
        }

        findViewById<Button>(R.id.get_banana_process_service).setOnClickListener {
            Tartarus.getRemoteService(BananaService::class.java)?.also {
                XLog.e(TAG, "香蕉进程服务结果：" + it.getBananaName())
                XLog.e(TAG, it.hashCode())
            }
        }
    }
}