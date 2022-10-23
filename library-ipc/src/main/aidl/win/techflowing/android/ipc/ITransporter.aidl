package win.techflowing.android.ipc;

/**
 * 用于代理 Service 跨进程调用的 Binder，每个进程只有一个实例，向 ServiceDispatcher 进程传递，进而向其它目标进程传递
 */
interface ITransporter {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes();
}