package win.techflowing.android.log.formatter.default

import win.techflowing.android.log.formatter.ThreadFormatter

/**
 * 线程信息默认格式化
 *
 * @author techflowing@gmail.com
 * @since 2022/5/19 12:32 上午
 */
class DefaultThreadFormatter : ThreadFormatter {

    override fun format(data: Thread): String {
        return data.name
    }
}