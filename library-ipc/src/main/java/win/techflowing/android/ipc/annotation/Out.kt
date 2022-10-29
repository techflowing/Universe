package win.techflowing.android.ipc.annotation

/**
 * Directional tag indicating which way the data goes, same as "out" tag in AIDL.
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Out()
