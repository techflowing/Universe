package win.techflowing.android.ipc.parameter.type

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import win.techflowing.android.ipc.core.IParcelableObject
import win.techflowing.android.ipc.log.Logger
import win.techflowing.android.ipc.util.ParcelableUtil
import java.lang.reflect.InvocationTargetException

/**
 * Type IO 类集中定义
 *
 * @author techflowing@gmail.com
 * @since 2022/11/14 23:50
 */
class EmptyType : TypeIO<Any?> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Any?) {
        // Nothing to do
    }

    override fun createFromParcel(source: Parcel): Any? {
        return null
    }
}

class ByteType : TypeIO<Byte> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Byte) {
        dest.writeByte(value)
    }

    override fun createFromParcel(source: Parcel): Byte {
        return source.readByte()
    }
}

class ShortType : TypeIO<Short> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Short) {
        dest.writeInt(value.toInt())
    }

    override fun createFromParcel(source: Parcel): Short {
        return source.readInt().toShort()
    }
}

class IntType : TypeIO<Int> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Int) {
        dest.writeInt(value)
    }

    override fun createFromParcel(source: Parcel): Int {
        return source.readInt()
    }
}

class LongType : TypeIO<Long> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Long) {
        dest.writeLong(value)
    }

    override fun createFromParcel(source: Parcel): Long {
        return source.readLong()
    }
}

class FloatType : TypeIO<Float> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Float) {
        dest.writeFloat(value)
    }

    override fun createFromParcel(source: Parcel): Float {
        return source.readFloat()
    }
}

class DoubleType : TypeIO<Double> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Double) {
        dest.writeDouble(value)
    }

    override fun createFromParcel(source: Parcel): Double {
        return source.readDouble()
    }
}

class BooleanType : TypeIO<Boolean> {
    override fun writeToParcel(dest: Parcel, flags: Int, value: Boolean) {
        dest.writeInt(if (value) 1 else 0)
    }

    override fun createFromParcel(source: Parcel): Boolean {
        return source.readInt() == 1
    }
}

class CharType : TypeIO<Char> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Char) {
        dest.writeInt(value.code)
    }

    override fun createFromParcel(source: Parcel): Char {
        return source.readInt().toChar()
    }
}

class ByteArrayType : ArrayTypeIO<ByteArray> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: ByteArray) {
        dest.writeByteArray(value)
    }

    override fun createFromParcel(source: Parcel): ByteArray? {
        return source.createByteArray()
    }

    override fun syncValueFromParcel(source: Parcel, target: ByteArray) {
        source.readByteArray(target)
    }

    override fun newInstance(length: Int): ByteArray {
        return ByteArray(length)
    }
}

class ShortArrayType : ArrayTypeIO<ShortArray> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: ShortArray) {
        val size = value.size
        dest.writeInt(size)
        for (i in 0 until size) {
            dest.writeInt(value[i].toInt())
        }
    }

    override fun createFromParcel(source: Parcel): ShortArray? {
        val size = source.readInt()
        return if (size >= 0 && size <= source.dataAvail() shr 2) {
            val array = newInstance(size)
            for (i in 0 until size) {
                array[i] = source.readInt().toShort()
            }
            array
        } else {
            null
        }
    }

    override fun syncValueFromParcel(source: Parcel, target: ShortArray) {
        val size = source.readInt()
        if (size == target.size) {
            for (i in 0 until size) {
                target[i] = source.readInt().toShort()
            }
        } else {
            throw IllegalStateException("bad array lengths")
        }
    }

    override fun newInstance(length: Int): ShortArray {
        return ShortArray(length)
    }
}

class IntArrayType : ArrayTypeIO<IntArray> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: IntArray) {
        dest.writeIntArray(value)
    }

    override fun createFromParcel(source: Parcel): IntArray? {
        return source.createIntArray()
    }

    override fun syncValueFromParcel(source: Parcel, target: IntArray) {
        source.readIntArray(target)
    }

    override fun newInstance(length: Int): IntArray {
        return IntArray(length)
    }
}

class LongArrayType : ArrayTypeIO<LongArray> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: LongArray) {
        dest.writeLongArray(value)
    }

    override fun createFromParcel(source: Parcel): LongArray? {
        return source.createLongArray()
    }

    override fun syncValueFromParcel(source: Parcel, target: LongArray) {
        source.readLongArray(target)
    }

    override fun newInstance(length: Int): LongArray {
        return LongArray(length)
    }
}

class FloatArrayType : ArrayTypeIO<FloatArray> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: FloatArray) {
        dest.writeFloatArray(value)
    }

    override fun createFromParcel(source: Parcel): FloatArray? {
        return source.createFloatArray()
    }

    override fun syncValueFromParcel(source: Parcel, target: FloatArray) {
        source.readFloatArray(target)
    }

    override fun newInstance(length: Int): FloatArray {
        return FloatArray(length)
    }
}

class DoubleArrayType : ArrayTypeIO<DoubleArray> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: DoubleArray) {
        dest.writeDoubleArray(value)
    }

    override fun createFromParcel(source: Parcel): DoubleArray? {
        return source.createDoubleArray()
    }

    override fun syncValueFromParcel(source: Parcel, target: DoubleArray) {
        source.readDoubleArray(target)
    }

    override fun newInstance(length: Int): DoubleArray {
        return DoubleArray(length)
    }
}

class BooleanArrayType : ArrayTypeIO<BooleanArray> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: BooleanArray) {
        dest.writeBooleanArray(value)
    }

    override fun createFromParcel(source: Parcel): BooleanArray? {
        return source.createBooleanArray()
    }

    override fun syncValueFromParcel(source: Parcel, target: BooleanArray) {
        source.readBooleanArray(target)
    }

    override fun newInstance(length: Int): BooleanArray {
        return BooleanArray(length)
    }
}

class CharArrayType : ArrayTypeIO<CharArray> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: CharArray) {
        dest.writeCharArray(value)
    }

    override fun createFromParcel(source: Parcel): CharArray? {
        return source.createCharArray()
    }

    override fun syncValueFromParcel(source: Parcel, target: CharArray) {
        source.readCharArray(target)
    }

    override fun newInstance(length: Int): CharArray {
        return CharArray(length)
    }
}

/**
 * 包装类数组转换
 */
inline fun <reified T> boxArrayConvert(source: Array<Any?>?): Array<T?>? {
    if (source == null) {
        return null
    }
    val result = arrayOfNulls<T>(source.size)
    for (index in source.indices) {
        result[index] = if (source[index] != null) source[index] as T else null
    }
    return result
}

/**
 * 复制数组元素
 */
fun <T> copyArray(source: Array<T?>?, target: Array<T?>) {
    if (source != null && source.size == target.size) {
        source.copyInto(target)
    } else {
        throw IllegalStateException("bad array")
    }
}

class BoxByteArrayType : ArrayTypeIO<Array<Byte?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Byte?>) {
        dest.writeArray(value)
    }

    override fun createFromParcel(source: Parcel): Array<Byte?>? {
        return boxArrayConvert(source.readArray(javaClass.classLoader))
    }

    override fun syncValueFromParcel(source: Parcel, target: Array<Byte?>) {
        copyArray(createFromParcel(source), target)
    }

    override fun newInstance(length: Int): Array<Byte?> {
        return arrayOfNulls(length)
    }
}

class BoxShortArrayType : ArrayTypeIO<Array<Short?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Short?>) {
        dest.writeArray(value)
    }

    override fun createFromParcel(source: Parcel): Array<Short?>? {
        return boxArrayConvert(source.readArray(javaClass.classLoader))
    }

    override fun syncValueFromParcel(source: Parcel, target: Array<Short?>) {
        copyArray(createFromParcel(source), target)
    }

    override fun newInstance(length: Int): Array<Short?> {
        return arrayOfNulls(length)
    }
}

class BoxIntArrayType : ArrayTypeIO<Array<Int?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Int?>) {
        dest.writeArray(value)
    }

    override fun createFromParcel(source: Parcel): Array<Int?>? {
        return boxArrayConvert(source.readArray(javaClass.classLoader))
    }

    override fun syncValueFromParcel(source: Parcel, target: Array<Int?>) {
        copyArray(createFromParcel(source), target)
    }

    override fun newInstance(length: Int): Array<Int?> {
        return arrayOfNulls(length)
    }
}

class BoxLongArrayType : ArrayTypeIO<Array<Long?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Long?>) {
        dest.writeArray(value)
    }

    override fun createFromParcel(source: Parcel): Array<Long?>? {
        return boxArrayConvert(source.readArray(javaClass.classLoader))
    }

    override fun syncValueFromParcel(source: Parcel, target: Array<Long?>) {
        copyArray(createFromParcel(source), target)
    }

    override fun newInstance(length: Int): Array<Long?> {
        return arrayOfNulls(length)
    }
}

class BoxFloatArrayType : ArrayTypeIO<Array<Float?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Float?>) {
        dest.writeArray(value)
    }

    override fun createFromParcel(source: Parcel): Array<Float?>? {
        return boxArrayConvert(source.readArray(javaClass.classLoader))
    }

    override fun syncValueFromParcel(source: Parcel, target: Array<Float?>) {
        copyArray(createFromParcel(source), target)
    }

    override fun newInstance(length: Int): Array<Float?> {
        return arrayOfNulls(length)
    }
}

class BoxDoubleArrayType : ArrayTypeIO<Array<Double?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Double?>) {
        dest.writeArray(value)
    }

    override fun createFromParcel(source: Parcel): Array<Double?>? {
        return boxArrayConvert(source.readArray(javaClass.classLoader))
    }

    override fun syncValueFromParcel(source: Parcel, target: Array<Double?>) {
        copyArray(createFromParcel(source), target)
    }

    override fun newInstance(length: Int): Array<Double?> {
        return arrayOfNulls(length)
    }
}

class BoxBooleanArrayType : ArrayTypeIO<Array<Boolean?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Boolean?>) {
        dest.writeArray(value)
    }

    override fun createFromParcel(source: Parcel): Array<Boolean?>? {
        return boxArrayConvert(source.readArray(javaClass.classLoader))
    }

    override fun syncValueFromParcel(source: Parcel, target: Array<Boolean?>) {
        copyArray(createFromParcel(source), target)
    }

    override fun newInstance(length: Int): Array<Boolean?> {
        return arrayOfNulls(length)
    }
}

class BoxCharArrayType : ArrayTypeIO<Array<Char?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Char?>) {
        dest.writeArray(value)
    }

    override fun createFromParcel(source: Parcel): Array<Char?>? {
        return boxArrayConvert(source.readArray(javaClass.classLoader))
    }

    override fun syncValueFromParcel(source: Parcel, target: Array<Char?>) {
        copyArray(createFromParcel(source), target)
    }

    override fun newInstance(length: Int): Array<Char?> {
        return arrayOfNulls(length)
    }
}

class StringType : TypeIO<String?> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: String?) {
        dest.writeString(value)
    }

    override fun createFromParcel(source: Parcel): String? {
        return source.readString()
    }
}

class StringArrayType : ArrayTypeIO<Array<String?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<String?>) {
        dest.writeStringArray(value)
    }

    override fun createFromParcel(source: Parcel): Array<String?>? {
        return source.createStringArray()
    }

    override fun syncValueFromParcel(source: Parcel, target: Array<String?>) {
        source.readStringArray(target)
    }

    override fun newInstance(length: Int): Array<String?> {
        return arrayOfNulls(length)
    }
}

class CharSequenceType : TypeIO<CharSequence?> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: CharSequence?) {
        TextUtils.writeToParcel(value, dest, 0)
    }

    override fun createFromParcel(source: Parcel): CharSequence? {
        return TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source)
    }
}

class CharSequenceArrayType : ArrayTypeIO<Array<CharSequence?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<CharSequence?>) {
        val size = value.size
        dest.writeInt(size)
        for (i in 0 until size) {
            TextUtils.writeToParcel(value[i], dest, 0)
        }
    }

    override fun createFromParcel(source: Parcel): Array<CharSequence?>? {
        val length = source.readInt()
        var result: Array<CharSequence?>? = null
        if (length > 0) {
            result = newInstance(length)
            for (i in 0 until length) {
                result[i] = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source)
            }
        }
        return result
    }

    override fun syncValueFromParcel(source: Parcel, target: Array<CharSequence?>) {
        val size = source.readInt()
        if (size == target.size) {
            for (i in 0 until size) {
                target[i] = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source)
            }
        } else {
            throw RuntimeException("bad array lengths")
        }
    }

    override fun newInstance(length: Int): Array<CharSequence?> {
        return arrayOfNulls(length)
    }
}

class ParcelableType : OutTypeIO<Parcelable> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Parcelable) {
        if (flags == Parcelable.PARCELABLE_WRITE_RETURN_VALUE) {
            value.writeToParcel(dest, flags)
        } else {
            dest.writeParcelable(value, flags)
        }
    }

    override fun createFromParcel(source: Parcel): Parcelable? {
        return source.readParcelable(javaClass.classLoader)
    }

    override fun syncValueFromParcel(source: Parcel, target: Parcelable) {
        if (target is IParcelableObject) {
            target.readFromParcel(source)
            return
        }
        val method = ParcelableUtil.getMethodReadFromParcel(target.javaClass)
            ?: throw IllegalArgumentException(
                "Parcelable parameter with @Out or @Inout annotation must " +
                        "declare the public readFromParcel() method or implements interface SuperParcelable. " +
                        "Error parameter type: " + target.javaClass.name
            )
        try {
            method.invoke(target, source)
        } catch (e: IllegalAccessException) {
            Logger.e(TAG, "Can't access method readFromParcel().", e)
        } catch (e: InvocationTargetException) {
            Logger.e(TAG, "Method readFromParcel() throws an exception.", e)
        }
    }

    companion object {
        private const val TAG = "ParcelableType"
    }
}

class ParcelableArrayType : ArrayTypeIO<Array<Parcelable?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Parcelable?>) {
        dest.writeString(value.javaClass.componentType.name)
        dest.writeParcelableArray(value, flags)
    }

    override fun createFromParcel(source: Parcel): Array<Parcelable?>? {
        val className = source.readString()
        val size = source.readInt()
        if (size < 0) {
            return null
        }
        val clazz = Class.forName(className!!)
        val array = java.lang.reflect.Array.newInstance(clazz, size) as Array<Parcelable?>
        for (i in 0 until size) {
            array[i] = source.readParcelable(clazz.classLoader)
        }
        return array
    }

    override fun syncValueFromParcel(source: Parcel, target: Array<Parcelable?>) {
        source.readString()
        val size = source.readInt()
        if (size == target.size) {
            for (index in 0 until size) {
                target[index] = source.readParcelable(javaClass.classLoader)
            }
        } else {
            throw IllegalStateException("bad array lengths")
        }
    }

    override fun newInstance(length: Int): Array<Parcelable?> {
        return arrayOfNulls(length)
    }
}

class ListType : OutTypeIO<MutableList<Any?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: MutableList<Any?>) {
        dest.writeList(value)
    }

    override fun createFromParcel(source: Parcel): MutableList<Any?>? {
        return source.readArrayList(javaClass.classLoader)
    }

    override fun syncValueFromParcel(source: Parcel, target: MutableList<Any?>) {
        val result = source.readArrayList(javaClass.classLoader)
        target.clear()
        result?.forEach {
            target.add(it)
        }
    }
}

class MapType : OutTypeIO<MutableMap<Any, Any?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: MutableMap<Any, Any?>) {
        dest.writeMap(value)
    }

    override fun createFromParcel(source: Parcel): MutableMap<Any, Any?>? {
        return source.readHashMap(javaClass.classLoader)
    }

    override fun syncValueFromParcel(source: Parcel, target: MutableMap<Any, Any?>) {
        val result = source.readHashMap(javaClass.classLoader)
        target.clear()
        result?.forEach {
            target[it.key] = it.value
        }
    }
}