package win.techflowing.android.ipc;

/**
 * 服务管理器，每个进程都有一个，负责管理本进程的 Service，以及注册、反注册，进程服务缓存等逻辑
 */
interface IServiceManager {

    /**
     * 向服务管理器注册服务分发器
     *
     * @param serviceDispatcherBinder 服务分发器的 Binder 对象
     */
    void registerServiceDispatcher(IBinder serviceDispatcherBinder);

    /**
     * 取消服务注册
     *
     * @param serviceCanonicalName 服务名称
     */
    void unregisterService(String serviceCanonicalName);
}