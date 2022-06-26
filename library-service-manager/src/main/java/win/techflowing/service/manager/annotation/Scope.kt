package win.techflowing.service.manager.annotation

/**
 * 标记 Service 实现的方式，单例或者每次创建新对象
 *
 * @author techflowing@gmail.com
 * @since 2022/6/4 9:53 下午
 */
enum class Scope {

    /** 单例 */
    SINGLETON,

    /** 每次都创建新的实例 */
    ONCE
}
