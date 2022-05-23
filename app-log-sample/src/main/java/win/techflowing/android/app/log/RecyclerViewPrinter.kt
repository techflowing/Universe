package win.techflowing.android.app.log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import win.techflowing.android.log.internal.LogLevel
import win.techflowing.android.log.printer.Printer
import win.techflowing.android.util.DateUtil

/**
 * 使用 RecyclerView 模拟 logcat 输出
 *
 * @property recyclerView 容器 RecyclerView
 * @author techflowing@gmail.com
 * @since 2022/5/23 11:15 下午
 */
class RecyclerViewPrinter(private val recyclerView: RecyclerView) : Printer {

    private val adapter: RecyclerViewAdapter = RecyclerViewAdapter(mutableListOf())

    init {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }

    override fun println(logLevel: Int, tag: String, msg: String) {
        adapter.printLog(logLevel, tag, msg)
        recyclerView.scrollToPosition(adapter.itemCount - 1)
    }

    inner class RecyclerViewAdapter(private val logList: MutableList<LogInfo>) : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_log_item, null, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val logItem = logList[position]
            val color: Int = getHighlightColor(logItem.logLevel)
            holder.messageView.setTextColor(color)

            holder.messageView.text = logItem.message
        }

        override fun getItemCount(): Int {
            return logList.size
        }

        fun printLog(logLevel: Int, tag: String, msg: String) {
            logList.add(LogInfo(logLevel, tag, msg))
            notifyItemInserted(logList.size - 1)
        }

        /**
         * Get the highlight color for specific log level.
         *
         * @param logLevel the specific log level
         * @return the highlight color
         */
        private fun getHighlightColor(logLevel: Int): Int {
            val highlightColor: Int = when (logLevel) {
                LogLevel.VERBOSE -> -0x444445
                LogLevel.DEBUG -> -0x1
                LogLevel.INFO -> -0x9578a7
                LogLevel.WARN -> -0x444ad7
                LogLevel.ERROR -> -0x9498
                else -> -0x100
            }
            return highlightColor
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageView: TextView by lazy {
            itemView.findViewById(R.id.message)
        }
    }

    data class LogInfo(val logLevel: Int, val tag: String, val msg: String) {

        val message: String by lazy {
            val format = "%s %s/%s: %s"
            val date = DateUtil.getFormatResult(System.currentTimeMillis(), "HH:mm:ss.SSS")
            String.format(format, date, LogLevel.getShortLevelName(logLevel), tag, msg)
        }
    }
}