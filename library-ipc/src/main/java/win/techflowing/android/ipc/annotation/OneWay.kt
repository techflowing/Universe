package win.techflowing.android.ipc.annotation

/**
 * Indicate a remote call does not block, it simply sends the transaction data and immediately
 * returns, same as "oneway" tag in AIDL.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OneWay
