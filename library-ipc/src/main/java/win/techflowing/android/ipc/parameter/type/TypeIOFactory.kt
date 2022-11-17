package win.techflowing.android.ipc.parameter.type

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import win.techflowing.android.ipc.ReadableParcelable
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

    override fun readFromParcel(source: Parcel, target: ByteArray) {
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

    override fun readFromParcel(source: Parcel, target: ShortArray) {
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

    override fun readFromParcel(source: Parcel, target: IntArray) {
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

    override fun readFromParcel(source: Parcel, target: LongArray) {
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

    override fun readFromParcel(source: Parcel, target: FloatArray) {
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

    override fun readFromParcel(source: Parcel, target: DoubleArray) {
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

    override fun readFromParcel(source: Parcel, target: BooleanArray) {
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

    override fun readFromParcel(source: Parcel, target: CharArray) {
        source.readCharArray(target)
    }

    override fun newInstance(length: Int): CharArray {
        return CharArray(length)
    }
}

/**
 * 包装类数组转换
 */
fun <T> boxArrayConvert(target: Array<T>, value: (index: Int) -> T) {
    for (index in target.indices) {
        target[index] = value.invoke(index)
    }
}

class BoxByteArrayType : ArrayTypeIO<Array<Byte>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Byte>) {
        dest.writeByteArray(value.toByteArray())
    }

    override fun createFromParcel(source: Parcel): Array<Byte>? {
        return source.createByteArray()?.toTypedArray()
    }

    override fun readFromParcel(source: Parcel, target: Array<Byte>) {
        val byteArray = ByteArray(target.size)
        source.readByteArray(byteArray)
        boxArrayConvert(target) {
            byteArray[it]
        }
    }

    override fun newInstance(length: Int): Array<Byte> {
        return Array(length) { 0 }
    }
}

class BoxShortArrayType : ArrayTypeIO<Array<Short>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Short>) {
        val size = value.size
        dest.writeInt(size)
        for (i in 0 until size) {
            dest.writeInt(value[i].toInt())
        }
    }

    override fun createFromParcel(source: Parcel): Array<Short>? {
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

    override fun readFromParcel(source: Parcel, target: Array<Short>) {
        val size = source.readInt()
        if (size == target.size) {
            for (i in 0 until size) {
                target[i] = source.readInt().toShort()
            }
        } else {
            throw IllegalStateException("bad array lengths")
        }
    }

    override fun newInstance(length: Int): Array<Short> {
        return Array(length) { 0 }
    }
}

class BoxIntArrayType : ArrayTypeIO<Array<Int>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Int>) {
        dest.writeIntArray(value.toIntArray())
    }

    override fun createFromParcel(source: Parcel): Array<Int>? {
        return source.createIntArray()?.toTypedArray()
    }

    override fun readFromParcel(source: Parcel, target: Array<Int>) {
        val intArray = IntArray(target.size)
        source.readIntArray(intArray)
        boxArrayConvert(target) {
            intArray[it]
        }
    }

    override fun newInstance(length: Int): Array<Int> {
        return Array(length) { 0 }
    }
}

class BoxLongArrayType : ArrayTypeIO<Array<Long>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Long>) {
        dest.writeLongArray(value.toLongArray())
    }

    override fun createFromParcel(source: Parcel): Array<Long>? {
        return source.createLongArray()?.toTypedArray()
    }

    override fun readFromParcel(source: Parcel, target: Array<Long>) {
        val longArray = LongArray(target.size)
        source.readLongArray(longArray)
        boxArrayConvert(target) {
            longArray[it]
        }
    }

    override fun newInstance(length: Int): Array<Long> {
        return Array(length) { 0 }
    }
}

class BoxFloatArrayType : ArrayTypeIO<Array<Float>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Float>) {
        dest.writeFloatArray(value.toFloatArray())
    }

    override fun createFromParcel(source: Parcel): Array<Float>? {
        return source.createFloatArray()?.toTypedArray()
    }

    override fun readFromParcel(source: Parcel, target: Array<Float>) {
        val floatArray = FloatArray(target.size)
        source.readFloatArray(floatArray)
        boxArrayConvert(target) {
            floatArray[it]
        }
    }

    override fun newInstance(length: Int): Array<Float> {
        return Array(length) { 0f }
    }
}

class BoxDoubleArrayType : ArrayTypeIO<Array<Double>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Double>) {
        dest.writeDoubleArray(value.toDoubleArray())
    }

    override fun createFromParcel(source: Parcel): Array<Double>? {
        return source.createDoubleArray()?.toTypedArray()
    }

    override fun readFromParcel(source: Parcel, target: Array<Double>) {
        val doubleArray = DoubleArray(target.size)
        source.readDoubleArray(doubleArray)
        boxArrayConvert(target) {
            doubleArray[it]
        }
    }

    override fun newInstance(length: Int): Array<Double> {
        return Array(length) { 0.0 }
    }
}

class BoxBooleanArrayType : ArrayTypeIO<Array<Boolean>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Boolean>) {
        dest.writeBooleanArray(value.toBooleanArray())
    }

    override fun createFromParcel(source: Parcel): Array<Boolean>? {
        return source.createBooleanArray()?.toTypedArray()
    }

    override fun readFromParcel(source: Parcel, target: Array<Boolean>) {
        val booleanArray = BooleanArray(target.size)
        source.readBooleanArray(booleanArray)
        boxArrayConvert(target) {
            booleanArray[it]
        }
    }

    override fun newInstance(length: Int): Array<Boolean> {
        return Array(length) { false }
    }
}

class BoxCharArrayType : ArrayTypeIO<Array<Char>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<Char>) {
        dest.writeCharArray(value.toCharArray())
    }

    override fun createFromParcel(source: Parcel): Array<Char>? {
        return source.createCharArray()?.toTypedArray()
    }

    override fun readFromParcel(source: Parcel, target: Array<Char>) {
        val charArray = CharArray(target.size)
        source.readCharArray(charArray)
        boxArrayConvert(target) {
            charArray[it]
        }
    }

    override fun newInstance(length: Int): Array<Char> {
        return Array(length) { ' ' }
    }
}

class StringType : TypeIO<String> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: String) {
        dest.writeString(value)
    }

    override fun createFromParcel(source: Parcel): String? {
        return source.readString()
    }
}

class StringArrayType : ArrayTypeIO<Array<String>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<String>) {
        dest.writeStringArray(value)
    }

    override fun createFromParcel(source: Parcel): Array<String>? {
        return source.createStringArray()
    }

    override fun readFromParcel(source: Parcel, target: Array<String>) {
        source.readStringArray(target)
    }

    override fun newInstance(length: Int): Array<String> {
        return Array(length) { "" }
    }
}

class CharSequenceType : TypeIO<CharSequence> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: CharSequence) {
        TextUtils.writeToParcel(value, dest, 0)
    }

    override fun createFromParcel(source: Parcel): CharSequence? {
        return TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source)
    }
}

class CharSequenceArrayType : ArrayTypeIO<Array<CharSequence>> {

    override fun readFromParcel(source: Parcel, target: Array<CharSequence>) {
        val size = source.readInt()
        if (size == target.size) {
            for (i in 0 until size) {
                target[i] = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source)
            }
        } else {
            throw RuntimeException("bad array lengths")
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int, value: Array<CharSequence>) {
        val size = value.size
        dest.writeInt(size)
        for (i in 0 until size) {
            TextUtils.writeToParcel(value[i], dest, 0)
        }
    }

    override fun createFromParcel(source: Parcel): Array<CharSequence>? {
        val array = mutableListOf<CharSequence>()
        val length = source.readInt()
        if (length >= 0) {
            for (i in 0 until length) {
                array.add(i, TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source))
            }
        }
        return array.toTypedArray()
    }

    override fun newInstance(length: Int): Array<CharSequence> {
        return Array(length) { "" }
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

    override fun readFromParcel(source: Parcel, target: Parcelable) {
        if (target is ReadableParcelable) {
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

class ListType : OutTypeIO<MutableList<Any?>> {

    override fun writeToParcel(dest: Parcel, flags: Int, value: MutableList<Any?>) {
        dest.writeList(value)
    }

    override fun createFromParcel(source: Parcel): MutableList<Any?>? {
        return source.readArrayList(javaClass.classLoader)
    }

    override fun readFromParcel(source: Parcel, target: MutableList<Any?>) {
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

    override fun readFromParcel(source: Parcel, target: MutableMap<Any, Any?>) {
        val result = source.readHashMap(javaClass.classLoader)
        target.clear()
        result?.forEach {
            target[it.key] = it.value
        }
    }
}