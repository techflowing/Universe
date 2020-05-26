package win.techflowing.android.sample.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import win.techflowing.android.R
import win.techflowing.android.sample.base.BaseActivity

/**
 * 功能说明
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2020/5/27 12:20 AM
 */
open class FirstActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_first
    }

    override fun initView() {
        super.initView()
        val view = findViewById<Button>(R.id.start_second_activity)
        view?.setOnClickListener {
            Log.e(getTag(), "点击跳转Activity2")
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(getTag(), "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e(getTag(), "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(getTag(), "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(getTag(), "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(getTag(), "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(getTag(), "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(getTag(), "onRestart")
    }

    open fun getTag(): String {
        return "Activity-1";
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.e(getTag(), "按下返回键")
    }
}