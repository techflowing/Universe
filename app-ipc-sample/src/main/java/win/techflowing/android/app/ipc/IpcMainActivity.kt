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
                basicTypeParamTransfer(it)
                basicArrayTypeParamTransfer(it)
            }
        }

        findViewById<Button>(R.id.get_banana_process_service).setOnClickListener {
            Tartarus.getRemoteService(BananaService::class.java)?.also {
                XLog.e(TAG, "香蕉进程服务结果：" + it.getBananaName())
                XLog.e(TAG, it.hashCode())
            }
        }
    }

    /**
     * 测试基础类型的参数传递
     */
    private fun basicTypeParamTransfer(appleService: AppleService) {
        appleService.basicType(1, 100, 1000, 10000, 1f, 1.0, true, 'A')
    }

    /**
     * 测试基础数组类型的参数传递
     */
    private fun basicArrayTypeParamTransfer(appleService: AppleService) {
        appleService.basicArrayType(
            ByteArray(3) { it.toByte() },
            ShortArray(3) { it.toShort() },
            IntArray(5) { it },
            LongArray(5) { (it * 10).toLong() },
            FloatArray(3) { it * 1f },
            DoubleArray(5) { it * 1.0 },
            BooleanArray(5) { it % 2 == 0 },
            CharArray(3) { 'A' + it }
        )
    }
}