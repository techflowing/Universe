package win.techflowing.android.app.log

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import win.techflowing.android.base.BaseActivity
import win.techflowing.android.log.LogConfiguration
import win.techflowing.android.log.XLog
import win.techflowing.android.log.formatter.ThreadFormatter

/**
 * 首页
 *
 * @author techflowing@gmail.com @version 1.0.0
 * @since 2022/5/6 11:24 下午
 */
class LogMainActivity : BaseActivity() {

    companion object {
        const val TAG = "LogMainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_sample_main)

        initXLog(findViewById<RecyclerView>(R.id.recycler_view))

        findViewById<Button>(R.id.test_normal).setOnClickListener {
            XLog.v(TAG, "这是一条普通的消息")
            XLog.v(TAG, "有 %d 条日志数据", 100)
        }

        findViewById<Button>(R.id.test_normal_error).setOnClickListener {
            XLog.e(TAG, "这是一条普通的异常消息")
        }

        findViewById<Button>(R.id.test_map).setOnClickListener {
            val map = mapOf<String, String>(
                "key1" to "value1",
                "key2" to "value2",
                "key3" to "value3",
                "key4" to "value4",
                "key5" to "value5",
                "key6" to "value6"
            )
            XLog.i(TAG, map)
        }

        findViewById<Button>(R.id.test_intent).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            XLog.d(TAG, intent)
        }

        findViewById<Button>(R.id.test_json).setOnClickListener {
            XLog.jsonForError(TAG, "{\"test\":\"value\",\"test2\":\"value2\"}")
        }

        findViewById<Button>(R.id.test_xml).setOnClickListener {
            XLog.xmlForError(
                TAG, "<resources>\n" +
                        "    <string name=\"app_name\">Universe</string>\n" +
                        "    <string name=\"app_name_log\">Log 日志</string>\n" +
                        "</resources>"
            )
        }
    }

    /** 初始化 XLog */
    private fun initXLog(recyclerView: RecyclerView) {
        val threadFormatter = object : ThreadFormatter {
            override fun format(data: Thread): String {
                return "(${data.name})-(${data.id})"
            }
        }

        XLog.init(
            LogConfiguration.Builder()
                .withThread(false)
                .threadFormatter(threadFormatter)
                .build(),
            RecyclerViewPrinter(recyclerView)
        )
    }
}