package win.techflowing.android.sample.funsample

import win.techflowing.android.sample.base.BaseActivity

/**
 * 功能使用说明基类
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2020/5/23 8:01 PM
 */
abstract class FunSampleBaseActivity : BaseActivity() {

    override fun backButtonEnable(): Boolean {
        return true
    }
}