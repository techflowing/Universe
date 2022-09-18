package win.techflowing.android.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 时间格式化相关类
 *
 * @author techflowing@gmail.com
 * @since 2018/12/31 14:50
 */
object DateUtil {
    /** 格式化到秒 */
    const val PATTERN_TO_SECOND = "yyyy-MM-dd HH:mm:ss"

    /** 格式化到分钟 */
    const val PATTERN_TO_MINUTES = "yyyy-MM-dd HH:mm"

    /** 格式化到天 */
    const val PATTERN_TO_DAY = "yyyy-MM-dd"

    /**
     * 获取格式化的结果
     *
     * @param pattern 格式化样式
     */
    fun getFormatResult(pattern: String?): String {
        return getFormatResult(Date(), pattern)
    }

    /**
     * 获取格式化的结果
     *
     * @param date 时间
     * @param pattern 格式化样式
     */
    fun getFormatResult(date: Date?, pattern: String?): String {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(date)
    }

    /**
     * 获取格式化的结果
     *
     * @param timestamp 毫秒时间戳
     * @param pattern 格式化样式
     */
    fun getFormatResult(timestamp: Long, pattern: String?): String {
        return getFormatResult(Date(timestamp), pattern)
    }

    /**
     * 获取时间戳
     *
     * @param source 源数据
     * @param pattern 格式化样式
     */
    @Throws(ParseException::class)
    fun getTimestamp(source: String?, pattern: String?): Long {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        val date = formatter.parse(source) ?: throw ParseException("parse result: date null", 0)
        return date.time
    }
}