package win.techflowing.android.sample.main

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import win.techflowing.android.R
import win.techflowing.android.sample.base.BaseActivity

/**
 * 示例类
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2020/5/23 8:05 PM
 */
class FunListActivity : BaseActivity() {

    @BindView(R.id.fun_list)
    lateinit var mFunListView: RecyclerView

    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mItemDecoration: RecyclerView.ItemDecoration
    private lateinit var mAdapter: FunListAdapter
    private lateinit var mFunDataManager: FunDataManager

    override fun getLayoutRes(): Int {
        return R.layout.activity_fun_list
    }

    override fun initVariable() {
        mFunDataManager = FunDataManager()
        mLayoutManager = LinearLayoutManager(this)
        mItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        mAdapter = FunListAdapter(this)
    }

    override fun loadData() {
        super.loadData()
        mAdapter.setDate(mFunDataManager.getFunModelList())
    }

    override fun initView() {
        mFunListView.layoutManager = mLayoutManager
        mFunListView.addItemDecoration(mItemDecoration)
        mFunListView.adapter = mAdapter
    }

    override fun getActivityTitle(): String? {
        return "功能示例"
    }
}