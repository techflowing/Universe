package win.techflowing.android.sample.main

import win.techflowing.android.sample.funsample.JniTestActivity
import win.techflowing.android.sample.funsample.LoggerSampleActivity
import win.techflowing.android.sample.test.FirstActivity

/**
 * 首页功能示例列表数据管理类
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2020/5/23 8:08 PM
 */
class FunDataManager {

    private var mFunModelList: MutableList<FunModel> = mutableListOf()

    init {
        mFunModelList.add(FunModel("Logger 日志库使用示例", LoggerSampleActivity::class.java))
        mFunModelList.add(FunModel("Activity 生命周期测试", FirstActivity::class.java))
        mFunModelList.add(FunModel("JNI学习", JniTestActivity::class.java))
    }

    fun getFunModelList(): List<FunModel> {
        return mFunModelList
    }
}