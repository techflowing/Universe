package win.techflowing.android

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 首页，提供所有功能模块的入口
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2022/5/6 11:24 下午
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val ACTION = "win.techflowing.android.app"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.list_application_sample)
        recyclerView.adapter = RecyclerViewAdapter(queryApplicationSampleList())
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    /**
     * 查询所有的 Application 示例信息
     */
    private fun queryApplicationSampleList(): MutableList<ItemData> {
        val list = mutableListOf<ItemData>()
        val intent = Intent()
        intent.`package` = this@MainActivity.packageName
        intent.action = ACTION

        val queryList = this@MainActivity.packageManager.queryIntentActivities(intent, 0)
        queryList.forEach {
            val title = it.activityInfo.loadLabel(this@MainActivity.packageManager)
            val activityInfo = packageManager.getActivityInfo(
                ComponentName(packageName, it.activityInfo.name),
                PackageManager.GET_META_DATA
            )
            val icon = activityInfo.metaData.getInt(it.activityInfo.name + "_icon")
            list.add(ItemData(it.activityInfo.name, title, icon))
        }
        return list
    }

    /**
     * RecyclerViewAdapter
     */
    inner class RecyclerViewAdapter(var dataList: MutableList<ItemData>) : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_application_sample, null, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = dataList[position]
            holder.icon.setImageResource(data.icon)
            holder.title.text = data.title
            holder.recyclerViewItem.setOnClickListener {
                val intent = Intent(holder.icon.context, Class.forName(data.activityClass))
                holder.icon.context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return dataList.size
        }
    }

    /**
     * Item View Holder
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recyclerViewItem: LinearLayout by lazy {
            itemView.findViewById<LinearLayout>(R.id.recycler_view_item)
        }

        val title: AppCompatTextView by lazy {
            itemView.findViewById<AppCompatTextView>(R.id.txt_name)
        }

        val icon: AppCompatImageView by lazy {
            itemView.findViewById<AppCompatImageView>(R.id.ima_icon)
        }
    }

    /**
     * Item 数据对象
     * @param activityClass Activity 类名
     * @param title 标题
     * @param icon Icon 资源值
     */
    data class ItemData(val activityClass: String, val title: CharSequence, val icon: Int)
}