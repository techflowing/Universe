package win.techflowing.android.ipc.core

import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.os.IBinder
import java.util.concurrent.ConcurrentHashMap

/**
 * ContentProvider 传递的 Cursor，包装 Binder 传递
 *
 * @author techflowing@gmail.com
 * @since 2022/9/25 21:51
 */
class BinderWrapperCursor(binder: IBinder) : MatrixCursor(arrayOf("col")) {

    private val extras: Bundle = Bundle()

    init {
        extras.putParcelable(KEY_BINDER_WRAPPER, BinderWrapper(binder))
    }

    override fun getExtras(): Bundle {
        return extras
    }

    companion object {
        const val KEY_BINDER_WRAPPER = "key_binder_wrapper"

        /** 缓存下，避免每次都构建新的 */
        private val cursorCacheMap: MutableMap<String, BinderWrapperCursor> = ConcurrentHashMap()

        /**
         * 构建 Cursor
         *
         * @param binder 要包装的 Binder 对象
         * @return
         */
        fun generateCursor(binder: IBinder): BinderWrapperCursor {
            var cursor = cursorCacheMap[binder.interfaceDescriptor]
            if (cursor != null) {
                return cursor
            }
            cursor = BinderWrapperCursor(binder)
            binder.interfaceDescriptor?.also {
                cursorCacheMap[it] = cursor
            }
            return cursor
        }

        /**
         * 从 Cursor 中解析出 Binder
         *
         * @param cursor Cursor
         * @return
         */
        fun stripBinder(cursor: Cursor): IBinder? {
            val bundle = cursor.extras
            bundle.classLoader = BinderWrapper::class.java.classLoader
            var binder: IBinder? = null
            bundle.getParcelable<BinderWrapper>(KEY_BINDER_WRAPPER)?.also {
                binder = it.getBinder()
            }
            return binder
        }
    }
}