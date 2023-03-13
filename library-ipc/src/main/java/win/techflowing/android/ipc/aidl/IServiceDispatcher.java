package win.techflowing.android.ipc.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * 所有服务管理分发，运行在分发进程，一般是主进程
 */
public interface IServiceDispatcher extends IInterface {

    /**
     * Local-side IPC implementation stub class.
     */
    abstract class Stub extends Binder implements IServiceDispatcher {

        private static final String DESCRIPTOR = "win.techflowing.android.ipc.aidl.IServiceDispatcher";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an win.techflowing.android.ipc.aidl.IServiceDispatcher interface,
         * generating a proxy if needed.
         */
        public static IServiceDispatcher asInterface(IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof IServiceDispatcher))) {
                return ((IServiceDispatcher) iin);
            }
            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_registerServiceManager: {
                    data.enforceInterface(descriptor);
                    int _arg0;
                    _arg0 = data.readInt();
                    IBinder _arg1;
                    _arg1 = data.readStrongBinder();
                    this.registerServiceManager(_arg0, IServiceManager.Stub.asInterface(_arg1));
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_registerService: {
                    data.enforceInterface(descriptor);
                    String _arg0;
                    _arg0 = data.readString();
                    int _arg1;
                    _arg1 = data.readInt();
                    String _arg2;
                    _arg2 = data.readString();
                    IBinder _arg3;
                    _arg3 = data.readStrongBinder();
                    this.registerService(_arg0, _arg1, _arg2, ITransporter.Stub.asInterface(_arg3));
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getServiceTransporterBinder: {
                    data.enforceInterface(descriptor);
                    String _arg0;
                    _arg0 = data.readString();
                    ITransporter transporter = this.getServiceTransporterBinder(_arg0);
                    reply.writeNoException();
                    reply.writeStrongBinder((transporter != null) ? transporter.asBinder() : null);
                    return true;
                }
                case TRANSACTION_unregisterService: {
                    data.enforceInterface(descriptor);
                    String _arg0;
                    _arg0 = data.readString();
                    this.unregisterService(_arg0);
                    reply.writeNoException();
                    return true;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        private static class Proxy implements IServiceDispatcher {

            private final IBinder mRemote;

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            /**
             * 向服务分发器注册本进程的服务管理器
             *
             * @param pid            进程号
             * @param serviceManager 服务管理器的 Binder 对象
             */
            @Override
            public void registerServiceManager(int pid, IServiceManager serviceManager) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(pid);
                    _data.writeStrongBinder((serviceManager != null) ? serviceManager.asBinder() : null);
                    mRemote.transact(IServiceDispatcher.Stub.TRANSACTION_registerServiceManager, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            /**
             * 注册服务
             *
             * @param serviceCanonicalName 服务名称
             * @param pid                  进程号
             * @param processName          进程名称
             * @param transporter          服务传输机 Binder
             */
            @Override
            public void registerService(String serviceCanonicalName, int pid, String processName, ITransporter transporter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(serviceCanonicalName);
                    _data.writeInt(pid);
                    _data.writeString(processName);
                    _data.writeStrongBinder((transporter != null) ? transporter.asBinder() : null);
                    mRemote.transact(IServiceDispatcher.Stub.TRANSACTION_registerService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            /**
             * 获取 Service 执行 Transporter Binder 对象
             *
             * @param serviceCanonicalName 服务名称
             */
            @Override
            public ITransporter getServiceTransporterBinder(String serviceCanonicalName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                ITransporter _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(serviceCanonicalName);
                    mRemote.transact(IServiceDispatcher.Stub.TRANSACTION_getServiceTransporterBinder, _data, _reply, 0);
                    _reply.readException();
                    _result = ITransporter.Stub.asInterface(_reply.readStrongBinder());
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            /**
             * 取消服务注册
             *
             * @param serviceCanonicalName 服务名称
             */
            @Override
            public void unregisterService(String serviceCanonicalName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(serviceCanonicalName);
                    mRemote.transact(IServiceDispatcher.Stub.TRANSACTION_unregisterService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        static final int TRANSACTION_registerServiceManager = (IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_registerService = (IBinder.FIRST_CALL_TRANSACTION + 1);
        static final int TRANSACTION_getServiceTransporterBinder = (IBinder.FIRST_CALL_TRANSACTION + 2);
        static final int TRANSACTION_unregisterService = (IBinder.FIRST_CALL_TRANSACTION + 3);
    }

    /**
     * 向服务分发器注册本进程的服务管理器
     *
     * @param pid            进程号
     * @param serviceManager 服务管理器的 Binder 对象
     */
    void registerServiceManager(int pid, IServiceManager serviceManager) throws RemoteException;

    /**
     * 注册服务
     *
     * @param serviceCanonicalName 服务名称
     * @param pid                  进程号
     * @param processName          进程名称
     * @param transporter          服务传输机 Binder
     */
    void registerService(String serviceCanonicalName, int pid, String processName, ITransporter transporter) throws RemoteException;

    /**
     * 获取 Service 执行 Transporter Binder 对象
     *
     * @param serviceCanonicalName 服务名称
     */
    ITransporter getServiceTransporterBinder(String serviceCanonicalName) throws RemoteException;

    /**
     * 取消服务注册
     *
     * @param serviceCanonicalName 服务名称
     */
    void unregisterService(String serviceCanonicalName) throws RemoteException;
}
