package win.techflowing.android.ipc.parameter.type

/**
 * 数组类型的 IO 接口
 *
 * @author techflowing@gmail.com
 * @since 2022/11/15 00:11
 */
interface ArrayTypeIO<T> : OutTypeIO<T> {

    /**
     * 创建数组
     *
     * @param length 数组长度
     * @return
     */
    fun newInstance(length: Int): T
}