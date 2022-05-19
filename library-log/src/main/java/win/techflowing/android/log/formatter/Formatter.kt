package win.techflowing.android.log.formatter

/**
 * 格式化数据为 字符串 形式
 *
 * @author techflowing@gmail.com
 * @since 2022/5/19 12:08 上午
 */
interface Formatter<T> {

    /**
     * 格式化数据为 字符串
     *
     * @param data 数据
     */
    fun format(data: T): String
}