package win.techflowing.android.log.formatter.default

import org.json.JSONArray
import org.json.JSONObject
import win.techflowing.android.log.formatter.JsonFormatter
import win.techflowing.android.log.internal.Platform

/**
 * JSON 数据默认格式化
 *
 * @author techflowing@gmail.com
 * @since 2022/5/19 12:32 上午
 */
class DefaultJsonFormatter : JsonFormatter {

    companion object {
        const val JSON_INDENT = 4
    }

    override fun format(json: String): String {
        if (json.trim().isEmpty()) {
            Platform.get().warn("JSON Empty")
            return ""
        }
        val formattedString = try {
            if (json.startsWith("{")) {
                val jsonObject = JSONObject(json)
                jsonObject.toString(JSON_INDENT)
            } else if (json.startsWith("[")) {
                val jsonArray = JSONArray(json)
                jsonArray.toString(JSON_INDENT)
            } else {
                Platform.get().warn("JSON should start with { or [")
                return json
            }
        } catch (e: Exception) {
            e.message?.let { Platform.get().warn(it) }
            return json
        }
        return formattedString
    }
}