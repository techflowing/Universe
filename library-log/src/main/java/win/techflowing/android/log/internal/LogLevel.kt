package win.techflowing.android.log.internal

/**
 * Log 分类
 *
 * @see android.util.Log
 * @author techflowing@gmail.com
 * @since 2022/5/19 9:09 下午
 */
class LogLevel {

    companion object {
        /** Priority constant for the println method; use Log.v. */
        const val VERBOSE = 2

        /** Priority constant for the println method; use Log.d. */
        const val DEBUG = 3

        /** Priority constant for the println method; use Log.i. */
        const val INFO = 4

        /** Priority constant for the println method; use Log.w. */
        const val WARN = 5

        /** Priority constant for the println method; use Log.e. */
        const val ERROR = 6

        /** Priority constant for the println method. */
        const val ASSERT = 7

        /** Log level for XLog#init, printing all logs. */
        const val ALL = Int.MIN_VALUE

        /**
         * Get a short name representing the specified log level.
         *
         * @param logLevel the log level to get short name for
         * @return the short name
         */
        fun getShortLevelName(logLevel: Int): String {
            val levelName: String = when (logLevel) {
                VERBOSE -> "V"
                DEBUG -> "D"
                INFO -> "I"
                WARN -> "W"
                ERROR -> "E"
                ASSERT -> "S"
                else -> if (logLevel < VERBOSE) {
                    "V-" + (VERBOSE - logLevel)
                } else {
                    "E+" + (logLevel - ERROR)
                }
            }
            return levelName
        }
    }
}