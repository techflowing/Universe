package win.techflowing.service.annotation

/**
 * 标记当前类是个 Service 实现类
 *
 * @author techflowing@gmail.com
 * @since 2022/6/2 1:37 上午
 */
@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class ServiceImpl(
    val scope: Scope = Scope.SINGLETON,
    val priority: Priority = Priority.DEFAULT
)
