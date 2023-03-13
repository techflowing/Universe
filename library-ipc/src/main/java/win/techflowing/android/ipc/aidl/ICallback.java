package win.techflowing.android.ipc.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import win.techflowing.android.ipc.call.CallbackRequest;
import win.techflowing.android.ipc.call.Response;

/**
 * 用户远程方法 Callback 回调
 */
public interface ICallback extends IInterface {

    /**
     * Local-side IPC implementation stub class.
     */
    abstract class Stub extends Binder implements ICallback {
        private static final String DESCRIPTOR = "win.techflowing.android.ipc.ICallback";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an win.techflowing.android.ipc.ICallback interface,
         * generating a proxy if needed.
         */
        public static ICallback asInterface(IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof ICallback))) {
                return ((ICallback) iin);
            }
            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws android.os.RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_callback: {
                    data.enforceInterface(descriptor);
                    CallbackRequest _arg0;
                    if ((0 != data.readInt())) {
                        _arg0 = CallbackRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    Response _result = this.callback(_arg0);
                    reply.writeNoException();
                    if ((_result != null)) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
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

        private static class Proxy implements ICallback {

            private final IBinder mRemote;

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

            @Override
            public Response callback(CallbackRequest request) throws android.os.RemoteException {
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
                    mRemote.transact(ICallback.Stub.TRANSACTION_callback, _data, _reply, 0);
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

            public static ICallback sDefaultImpl;
        }

        static final int TRANSACTION_callback = (IBinder.FIRST_CALL_TRANSACTION + 0);
    }

    @NonNull
    Response callback(CallbackRequest request) throws android.os.RemoteException;
}
