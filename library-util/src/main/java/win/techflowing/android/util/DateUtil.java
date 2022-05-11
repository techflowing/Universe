package win.techflowing.android.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间格式化相关类
 *
 * @author techflowing@gmail.com
 * @since 2018/12/31 14:50
 */
public class DateUtil {

    /** 格式化到秒 */
    public static final String PATTERN_TO_SECOND = "yyyy-MM-dd HH:mm:ss";
    /** 格式化到分钟 */
    public static final String PATTERN_TO_MINUTES = "yyyy-MM-dd HH:mm";
    /** 格式化到天 */
    public static final String PATTERN_TO_DAY = "yyyy-MM-dd";

    /**
     * 获取格式化的结果
     *
     * @param pattern 格式化样式
     */
    public static String getFormatResult(String pattern) {
        return getFormatResult(new Date(), pattern);
    }

    /**
     * 获取格式化的结果
     *
     * @param date    时间
     * @param pattern 格式化样式
     */
    public static String getFormatResult(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        return formatter.format(date);
    }

    /**
     * 获取格式化的结果
     *
     * @param timestamp 毫秒时间戳
     * @param pattern   格式化样式
     */
    public static String getFormatResult(long timestamp, String pattern) {
        return getFormatResult(new Date(timestamp), pattern);
    }

    /**
     * 获取时间戳
     *
     * @param source  源数据
     * @param pattern 格式化样式
     */
    public static long getTimestamp(String source, String pattern) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        Date date = formatter.parse(source);
        if (date == null) {
            throw new ParseException("parse result: date null", 0);
        }
        return date.getTime();
    }
}
