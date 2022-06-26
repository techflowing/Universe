package win.techflowing.service.manager.annotation

/**
 * 标记当前类是个 Service 实现类
 *
 * @property scope 单例还是每次新对象，修改默认值需要同步修改 plugin 实现
 * @property priority 优先级，修改默认值需要同步修改 plugin 实现
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
