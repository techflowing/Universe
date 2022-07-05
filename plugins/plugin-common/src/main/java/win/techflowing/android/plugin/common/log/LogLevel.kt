package win.techflowing.android.plugin.common.log

/**
 * Log 分类
 *
 * @author techflowing@gmail.com
 * @since 2022/5/19 9:09 下午
 */
enum class LogLevel(val desc: String, val shortDesc: String, val level: Int) {

    DEBUG("DEBUG", "D", 3),
    INFO("INFO", "I", 4),
    WARN("WARN", "W", 5),
    ERROR("ERROR", "E", 6);
}