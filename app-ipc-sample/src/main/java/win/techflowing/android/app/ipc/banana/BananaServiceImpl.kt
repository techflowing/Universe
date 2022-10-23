package win.techflowing.android.app.ipc.banana

/**
 * Apple Service 实现
 *
 * @author techflowing@gmail.com
 * @since 2022/10/16 22:59
 */
class BananaServiceImpl : BananaService {

    override fun getBananaName(): String {
        return "香蕉"
    }
}