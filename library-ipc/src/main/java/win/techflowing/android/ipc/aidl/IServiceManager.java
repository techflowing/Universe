package win.techflowing.android.ipc.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * 服务管理器，每个进程都有一个，负责管理本进程的 Service，以及注册、反注册，进程服务缓存等逻辑
 */
public interface IServiceManager extends IInterface {

    /**
     * Local-side IPC implementation stub class.
     */
    abstract class Stub extends Binder implements IServiceManager {
        private static final String DESCRIPTOR = "win.techflowing.android.ipc.aidl.IServiceManager";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an win.techflowing.android.ipc.aidl.IServiceManager interface,
         * generating a proxy if needed.
         */
        public static IServiceManager asInterface(IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof IServiceManager))) {
                return ((IServiceManager) iin);
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
                case TRANSACTION_registerServiceDispatcher: {
                    data.enforceInterface(descriptor);
                    IBinder _arg0;
                    _arg0 = data.readStrongBinder();
                    this.registerServiceDispatcher(IServiceDispatcher.Stub.asInterface(_arg0));
                    reply.writeNoException();
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

        private static class Proxy implements IServiceManager {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            /**
             * 向服务管理器注册服务分发器
             *
             * @param serviceDispatcher 服务分发器的 Binder 对象
             */
            @Override
            public void registerServiceDispatcher(IServiceDispatcher serviceDispatcher) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((serviceDispatcher != null) ? serviceDispatcher.asBinder() : null);
                    mRemote.transact(IServiceManager.Stub.TRANSACTION_registerServiceDispatcher, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
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
                    mRemote.transact(IServiceManager.Stub.TRANSACTION_unregisterService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        static final int TRANSACTION_registerServiceDispatcher = (IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_unregisterService = (IBinder.FIRST_CALL_TRANSACTION + 1);
    }

    /**
     * 向服务管理器注册服务分发器
     *
     * @param serviceDispatcher 服务分发器的 Binder 对象
     */
    void registerServiceDispatcher(IServiceDispatcher serviceDispatcher) throws RemoteException;

    /**
     * 取消服务注册
     *
     * @param serviceCanonicalName 服务名称
     */
    void unregisterService(String serviceCanonicalName) throws RemoteException;
}
