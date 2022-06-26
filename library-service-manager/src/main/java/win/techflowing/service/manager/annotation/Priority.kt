package win.techflowing.service.manager.annotation

/**
 * 定义Service 实现优先级
 *
 * @author techflowing@gmail.com
 * @since 2022/6/4 10:01 下午
 */
enum class Priority(val value: Int) {

    /** 最低优先级 */
    LOW(-100),

    /** 默认优先级 */
    DEFAULT(0),

    /** 高优先级 */
    HIGH(100),

    /** 最高优先级 */
    HIGHEST(10000)
}