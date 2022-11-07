package win.techflowing.android.ipc.aidl;

import android.os.Binder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import win.techflowing.android.ipc.call.Request;
import win.techflowing.android.ipc.call.Response;

/**
 * 用于代理 Service 跨进程调用的 Binder，每个进程只有一个实例，向 ServiceDispatcher 进程传递，进而向其它目标进程传递
 */
public interface ITransporter extends android.os.IInterface {
    /**
     * Default implementation for ITransporter.
     */
    public static class Default implements ITransporter {
        /**
         * 执行请求
         *
         * @param request 请求信息
         * @return 远程请求执行结果
         */
        @Override
        public Response execute(@NonNull Request request) throws android.os.RemoteException {
            return null;
        }

        @Override
        public android.os.IBinder asBinder() {
            return null;
        }
    }

    /**
     * Local-side IPC implementation stub class.
     */
    public static abstract class Stub extends Binder implements ITransporter {
        private static final java.lang.String DESCRIPTOR = "win.techflowing.android.ipc.aidl.ITransporter";

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
        public static ITransporter asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof ITransporter))) {
                return ((ITransporter) iin);
            }
            return new ITransporter.Stub.Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            java.lang.String descriptor = DESCRIPTOR;
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
                    reply.writeNoException();
                    if ((_result != null)) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        private static class Proxy implements ITransporter {

            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public java.lang.String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            /**
             * 执行请求
             *
             * @param request 请求信息
             * @return 远程请求执行结果
             */
            @Override
            public Response execute(@NonNull Request request) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                Response _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((request != null)) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = mRemote.transact(Stub.TRANSACTION_execute, _data, _reply, 0);
                    if (!_status && getDefaultImpl() != null) {
                        return getDefaultImpl().execute(request);
                    }
                    _reply.readException();
                    if ((0 != _reply.readInt())) {
                        _result = Response.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            public static ITransporter sDefaultImpl;
        }

        static final int TRANSACTION_execute = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);

        public static boolean setDefaultImpl(ITransporter impl) {
            // Only one user of this interface can use this function
            // at a time. This is a heuristic to detect if two different
            // users in the same process use this function.
            if (Stub.Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Stub.Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ITransporter getDefaultImpl() {
            return Stub.Proxy.sDefaultImpl;
        }
    }

    /**
     * 执行请求
     *
     * @param request 请求信息
     * @return 远程请求执行结果
     */
    @Nullable
    Response execute(@NonNull Request request) throws android.os.RemoteException;
}
