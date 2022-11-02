package win.techflowing.android.ipc.call.adapter

import win.techflowing.android.ipc.call.Call
import java.lang.reflect.Type

/**
 *  A [CallAdapterFactory] which uses the original [Call], just return as is.
 *
 * @author techflowing@gmail.com
 * @since 2022/11/1 23:18
 */
class OriginalCallAdapterFactory : CallAdapterFactory() {

    override fun get(returnType: Type, annotations: Array<Annotation>): CallAdapter<*, *>? {
        TODO("Not yet implemented")
    }
}