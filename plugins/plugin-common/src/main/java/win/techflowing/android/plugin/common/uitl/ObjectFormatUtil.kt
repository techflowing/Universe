package win.techflowing.android.plugin.common.uitl

/**
 * Class desc、name 等格式转换
 *
 * 以 BdpService 类为例
 * 1、Desc格式：        Lcom/bytedance/bdp/bdpbase/annotation/BdpService;
 * 3、Path格式：        com/bytedance/bdp/bdpbase/annotation/BdpService.class
 * 3、普通 Name格式：    com.bytedance.bdp.bdpbase.annotation.BdpService
 * 4、ASM Name格式：    com/bytedance/bdp/bdpbase/annotation/BdpService
 *
 * @author techflowing@gmail.com
 * @since 2022/6/4 11:41 PM
 */
object ObjectFormatUtil {

    fun asmNameToDesc(asmName: String): String {
        return "L$asmName;"
    }

    fun asmNameToName(asmName: String): String {
        return asmName.replace('/', '.')
    }

    fun asmNameToPath(asmName: String): String {
        return "$asmName.class"
    }

    fun nameToDesc(name: String): String {
        return "L${name.replace('.', '/')};"
    }

    fun nameToAsmName(name: String): String {
        return name.replace('.', '/')
    }

    fun pathToName(path: String): String {
        return path.substringBefore(".class").replace('/', '.')
    }

    fun nameToPath(name: String): String {
        return "${name.replace('.', '/')}.class"
    }

    fun descToName(desc: String): String {
        return desc.substringAfter("L").substringBefore(";").replace('/', '.')
    }

    fun descToAsmName(desc: String): String {
        return desc.substringAfter("L").substringBefore(";")
    }
}