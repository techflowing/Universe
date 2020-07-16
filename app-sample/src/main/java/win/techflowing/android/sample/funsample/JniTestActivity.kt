package win.techflowing.android.sample.funsample

import android.content.Intent
import android.widget.Button
import win.techflowing.android.R
import win.techflowing.android.jni.JniLearningActivity

/**
 * JNI 测试
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2020/5/23 8:02 PM
 */
class JniTestActivity : FunSampleBaseActivity() {

    override fun initVariable() {

    }

    override fun initView() {
        findViewById<Button>(R.id.btn_test_jni).setOnClickListener {
            startActivity(Intent(this, JniLearningActivity::class.java))
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_jni_test
    }

    override fun getActivityTitle(): String {
        return "JNI示例"
    }
}