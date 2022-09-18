package win.techflowing.android.ipc

/**
 * Service 管理，运行于每一个进程内
 *
 * @author techflowing@gmail.com
 * @since 2022/9/17 17:07
 */
class ServiceManager private constructor() {


    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ServiceManager()
        }
    }
}