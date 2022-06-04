package win.techflowing.android.plugin.service

/**
 * Plugin 配置参数
 *
 * @author techflowing@gmail.com
 * @since 2022/5/29 9:21 下午
 */
open class PluginExtension {

    var appName: String? = null
    
    companion object {
        const val NAME = "serviceManager"
    }
}