package win.techflowing.android.log.formatter.default.objects

import android.content.Intent
import win.techflowing.android.log.formatter.ObjectFormatter
import win.techflowing.android.log.util.ObjectToStringUtil

/**
 * Intent 数据格式化
 *
 * @author techflowing@gmail.com
 * @since 2022/5/19 12:35 上午
 */
class IntentFormatter : ObjectFormatter<Intent> {

    override fun format(data: Intent): String {
        return ObjectToStringUtil.intentToString(data)
    }
}