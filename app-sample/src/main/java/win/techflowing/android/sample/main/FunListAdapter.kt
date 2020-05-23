package win.techflowing.android.sample.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import win.techflowing.android.R

/**
 * 示例列表Adapter
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2020/5/23 8:18 PM
 */
class FunListAdapter(private val context: Context) : RecyclerView.Adapter<FunListAdapter.ViewHolder>() {

    private var funModelList: List<FunModel>? = null

    fun setDate(funModelList: List<FunModel>) {
        this.funModelList = funModelList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_fun_list, parent, false))
    }

    override fun getItemCount(): Int {
        return funModelList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val funModel = funModelList?.get(position)
        holder.title.text = funModel?.name
        holder.itemView.setOnClickListener {
            val intent = Intent(context, funModel?.funActivityClass)
            context.startActivity(intent)
        }
    }

    class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.txt_fun_title)
        lateinit var title: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}