package win.techflowing.android.log.printer

/**
 * Printer 集合，封装多个 Printer
 *
 * @author techflowing@gmail.com
 * @since 2022/5/19 9:11 下午
 */
class PrinterSet(private val printers: Array<out Printer>) : Printer {

    override fun println(logLevel: Int, tag: String, msg: String) {
        for (printer in printers) {
            printer.println(logLevel, tag, msg)
        }
    }
}