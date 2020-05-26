package win.techflowing.android.sample.test

import win.techflowing.android.R

/**
 * 功能说明
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2020/5/27 12:20 AM
 */
class SecondActivity : FirstActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_second
    }

    override fun getTag(): String {
        return "Activity-2"
    }
}