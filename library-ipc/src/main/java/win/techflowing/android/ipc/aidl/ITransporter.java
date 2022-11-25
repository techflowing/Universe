package win.techflowing.android.ipc.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

import win.techflowing.android.ipc.call.Request;
import win.techflowing.android.ipc.call.Response;

/**
 * 用于代理 Service 跨进程调用的 Binder，每个进程只有一个实例，向 ServiceDispatcher 进程传递，进而向其它目标进程传递
 */
public interface ITransporter extends IInterface {
    /**
     * Local-side IPC implementation stub class.
     */
    abstract class Stub extends Binder implements ITransporter {
        private static final String DESCRIPTOR = "win.techflowing.android.ipc.aidl.ITransporter";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an win.techflowing.android.ipc.aidl.ITransporter interface,
         * generating a proxy if needed.
         */
        public static ITransporter asInterface(IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof ITransporter))) {
                return ((ITransporter) iin);
            }
            return new ITransporter.Stub.Proxy(obj);
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
                case TRANSACTION_execute: {
                    data.enforceInterface(descriptor);
                    Request _arg0;
                    if ((0 != data.readInt())) {
                        _arg0 = Request.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    Response _result = this.execute(_arg0);
                    if ((flags & IBinder.FLAG_ONEWAY) != 0) {
                        // One-way mode just execute and return directly.
                        return true;
                    }
                    reply.writeNoException();
                    if ((_result != null)) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
                    } else {
                        reply.writeInt(0);
                    }
                    if ((_arg0 != null)) {
                        reply.writeInt(1);
                        _arg0.writeToParcel(reply, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                }
                case TRANSACTION_register: {
                    data.enforceInterface(DESCRIPTOR);
                    ICallback _arg0;
                    _arg0 = ICallback.Stub.asInterface(data.readStrongBinder());
                    this.registerCallback(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_unRegister: {
                    data.enforceInterface(DESCRIPTOR);
                    ICallback _arg0;
                    _arg0 = ICallback.Stub.asInterface(data.readStrongBinder());
                    this.unregisterCallback(_arg0);
                    reply.writeNoException();
                    return true;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        private static class Proxy implements ITransporter {

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
             * 执行请求
             *
             * @param request 请求信息
             * @return 远程请求执行结果
             */
            @Override
            public Response execute(Request request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                Response _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((request != null)) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    // One-way mode just transact and return directly.
                    if (request != null && request.isOneway()) {
                        mRemote.transact(Stub.TRANSACTION_execute, _data, null, IBinder.FLAG_ONEWAY);
                        return null;
                    }
                    mRemote.transact(Stub.TRANSACTION_execute, _data, _reply, 0);
                    _reply.readException();
                    if ((0 != _reply.readInt())) {
                        _result = Response.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    // InOut, Out 类型的参数读取远端返回的 Request 数据
                    if ((0 != _reply.readInt()) && request != null) {
                        request.syncRemoteValueFromParcel(_reply);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public void registerCallback(ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((callback != null)) ? (callback.asBinder()) : (null)));
                    mRemote.transact(Stub.TRANSACTION_register, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public void unregisterCallback(ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((callback != null)) ? (callback.asBinder()) : (null)));
                    mRemote.transact(Stub.TRANSACTION_unRegister, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        static final int TRANSACTION_execute = (IBinder.FIRST_CALL_TRANSACTION);
        static final int TRANSACTION_register = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
        static final int TRANSACTION_unRegister = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    }

    /**
     * 执行请求
     *
     * @param request 请求信息
     * @return 远程请求执行结果
     */
    Response execute(Request request) throws RemoteException;

    /**
     * 注册 Callback 传输 Binder
     *
     * @param callback Binder
     */
    void registerCallback(ICallback callback) throws RemoteException;

    /**
     * 反注册 Callback 传输 Binder
     *
     * @param callback Binder
     */
    void unregisterCallback(ICallback callback) throws RemoteException;
}
