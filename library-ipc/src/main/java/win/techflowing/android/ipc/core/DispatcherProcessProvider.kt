package win.techflowing.android.ipc.core

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import win.techflowing.android.ipc.log.Logger

/**
 * 运行于 Dispatcher 进程的 Provider，用于同步获取 ServiceDispatcher Binder 对象
 *
 * @author techflowing@gmail.com
 * @since 2022/9/25 18:37
 */
class DispatcherProcessProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor {
        Logger.d(TAG, "query uri: ${uri.authority}")
        return BinderWrapperCursor.generateCursor(ServiceDispatcher.getInstance().asBinder())
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    companion object {
        const val TAG = "DispatcherProcessProvider"

        fun getProviderUri(context: Context): Uri {
            return Uri.parse("content://${context.packageName}.ipc.service.dispatcher")
        }
    }
}