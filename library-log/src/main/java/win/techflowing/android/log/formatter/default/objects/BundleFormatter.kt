package win.techflowing.android.log.formatter.default.objects

import android.os.Bundle
import win.techflowing.android.log.formatter.ObjectFormatter
import win.techflowing.android.log.util.ObjectToStringUtil

/**
 * Bundler 数据格式化
 *
 * @author techflowing@gmail.com
 * @since 2022/5/19 12:35 上午
 */
class BundleFormatter : ObjectFormatter<Bundle> {

    override fun format(data: Bundle): String {
        return ObjectToStringUtil.bundleToString(data)
    }
}