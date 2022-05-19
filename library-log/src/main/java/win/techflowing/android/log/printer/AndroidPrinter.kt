package win.techflowing.android.log.printer

import android.util.Log
import kotlin.math.min

/**
 * Android 日志输出
 *
 * @author techflowing@gmail.com
 * @since 2022/5/19 9:23 下午
 */
class AndroidPrinter(private val maxChunkSize: Int) : Printer {

    companion object {
        /**
         * Generally, android has a default length limit of 4096 for
         * single log, but some device(like HUAWEI) has its own shorter
         * limit, so we just use 4000 and wish it could run well in all
         * devices.
         */
        const val DEFAULT_MAX_CHUNK_SIZE = 4000
    }

    constructor() : this(DEFAULT_MAX_CHUNK_SIZE)

    override fun println(logLevel: Int, tag: String, msg: String) {
        val msgLength = msg.length
        var start = 0
        var end: Int
        while (start < msgLength) {
            if (msg[start] == '\n') {
                start++
                continue
            }
            end = min(start + maxChunkSize, msgLength)
            end = adjustEnd(msg, start, end)
            printChunk(logLevel, tag, msg.substring(start, end))
            start = end
        }
    }

    /** Move the end to the nearest line separator('\n') (if exist). */
    private fun adjustEnd(msg: String, start: Int, originEnd: Int): Int {
        if (originEnd == msg.length) {
            // Already end of message.
            return originEnd
        }
        if (msg[originEnd] == '\n') {
            // Already prior to '\n'.
            return originEnd
        }
        // Search back for '\n'.
        var last = originEnd - 1
        while (start < last) {
            if (msg[last] == '\n') {
                return last
            }
            last--
        }
        return originEnd
    }

    /**
     * Print single chunk of log in new line.
     *
     * @param logLevel the level of log
     * @param tag the tag of log
     * @param msg the msg of log
     */
    private fun printChunk(logLevel: Int, tag: String, msg: String) {
        Log.println(logLevel, tag, msg)
    }
}