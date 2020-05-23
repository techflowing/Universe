package win.techflowing.android.sample.main

import win.techflowing.android.sample.base.BaseActivity
import win.techflowing.android.sample.base.BaseModel

/**
 * 功能示例 Model
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2020/5/23 8:10 PM
 */
class FunModel(
    val name: String,
    val funActivityClass: Class<out BaseActivity>
) : BaseModel() {

}