package win.techflowing.android.ipc;

/**
 * 所有服务管理分发，运行在分发进程，一般是主进程
 */
interface IServiceDispatcher {

    /**
     * 向服务分发器注册本进程的服务管理器
     *
     * @param pid                  进程号
     * @param serviceManagerBinder 服务管理器的 Binder 对象
     */
    void registerServiceManager(int pid, IBinder serviceManagerBinder);

    /**
     * 注册服务
     *
     * @param serviceCanonicalName 服务名称
     * @param pid                  进程号
     * @param processName          进程名称
     * @param transporterBinder    服务传输机 Binder
     */
    void registerService(String serviceCanonicalName, int pid, String processName, IBinder transporterBinder);

    /**
     * 取消服务注册
     *
     * @param serviceCanonicalName 服务名称
     */
    void unregisterService(String serviceCanonicalName);
}