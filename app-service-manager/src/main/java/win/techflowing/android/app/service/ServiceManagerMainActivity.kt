package win.techflowing.android.app.service

import android.os.Bundle
import android.util.Log
import android.widget.Button
import win.techflowing.android.base.BaseActivity
import win.techflowing.service.manager.ServiceManager

/**
 * 首页
 *
 * @author techflowing@gmail.com @version 1.0.0
 * @since 2022/5/6 11:24 下午
 */
class ServiceManagerMainActivity : BaseActivity() {

    companion object {
        const val TAG = "ServiceManager"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_manager_main)

        findViewById<Button>(R.id.test_once_service).setOnClickListener {
            val start = System.currentTimeMillis()
            ServiceManager.of(OnceService::class.java)?.sayHello()
            Log.e(TAG, "create 耗时 ${System.currentTimeMillis() - start}")
        }

        findViewById<Button>(R.id.test_singleton_service).setOnClickListener {
            for (i in 0 until 100) {
                Thread {
                    Log.e(TAG, "SingletonService onCreate $i")
                    ServiceManager.of(SingletonService::class.java)?.sayHello()
                }.start()
            }
        }

        findViewById<Button>(R.id.test_multiple_priority_service).setOnClickListener {
            ServiceManager.of(MultiplePriorityService::class.java)?.sayHello()
        }

        findViewById<Button>(R.id.test_multiple_impl_service).setOnClickListener {
            // 目前会创建两个不同的 Provider，后续看需求决定是否优化
            ServiceManager.of(MultipleServiceOne::class.java)?.sayHelloOne()
            ServiceManager.of(MultipleServiceTwo::class.java)?.sayHelloTwo()
        }
    }
}