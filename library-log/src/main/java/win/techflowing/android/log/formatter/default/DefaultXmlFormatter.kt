package win.techflowing.android.log.formatter.default

import win.techflowing.android.log.formatter.XmlFormatter
import win.techflowing.android.log.internal.Platform
import java.io.StringReader
import java.io.StringWriter
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

/**
 * XML 数据默认格式化
 *
 * @author techflowing@gmail.com
 * @since 2022/5/19 12:32 上午
 */
class DefaultXmlFormatter : XmlFormatter {

    companion object {
        const val XML_INDENT = 4
    }

    override fun format(xml: String): String {
        if (xml.trim().isEmpty()) {
            Platform.get().warn("XML empty.")
            return ""
        }
        val formattedString: String = try {
            val xmlInput: Source = StreamSource(StringReader(xml))
            val xmlOutput = StreamResult(StringWriter())
            val transformer = TransformerFactory.newInstance().newTransformer()
            transformer.setOutputProperty(OutputKeys.INDENT, "yes")
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", XML_INDENT.toString())
            transformer.transform(xmlInput, xmlOutput)
            xmlOutput.writer.toString().replaceFirst(">".toRegex(), ">" + System.lineSeparator())
        } catch (e: Exception) {
            e.message?.let { Platform.get().warn(it) }
            return xml
        }
        return formattedString
    }
}