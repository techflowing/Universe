package win.techflowing.android.ipc.annotation

/**
 * Indicate a parameter is a remote callback type.
 *
 * @author techflowing@gmail.com
 * @since 2022/10/26 23:05
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteCallback
