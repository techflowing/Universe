package win.techflowing.android.ipc.parameter.type

import android.os.Parcelable

/**
 * Type 表，包含 flag 数字标识，Class 类型映射表，Parcelable读取/写入工具类
 *
 * @author techflowing@gmail.com
 * @since 2022/11/14 23:37
 */
enum class TypeTable(
    private val classType: Array<Class<*>>,
    private val io: TypeIO<*>
) {
    // 空类型，兜底值、错误值
    EMPTY(arrayOf(), EmptyType()),

    // 8个基本类型，Kotlin 通过初始值来区分包装类、非包装类，这里统一使用包装类型
    BYTE(arrayOf(Byte::class.javaObjectType, Byte::class.java), ByteType()),
    SHORT(arrayOf(Short::class.javaObjectType, Short::class.java), ShortType()),
    INT(arrayOf(Int::class.javaObjectType, Int::class.java), IntType()),
    LONG(arrayOf(Long::class.javaObjectType, Long::class.java), LongType()),
    FLOAT(arrayOf(Float::class.javaObjectType, Float::class.java), FloatType()),
    DOUBLE(arrayOf(Double::class.javaObjectType, Double::class.java), DoubleType()),
    BOOLEAN(arrayOf(Boolean::class.javaObjectType, Boolean::class.java), BooleanType()),
    CHAR(arrayOf(Char::class.javaObjectType, Char::class.java), CharType()),

    // 8个基本类型的数组
    BYTE_ARRAY(arrayOf(ByteArray::class.java), ByteArrayType()),
    SHORT_ARRAY(arrayOf(ShortArray::class.java), ShortArrayType()),
    INT_ARRAY(arrayOf(IntArray::class.java), IntArrayType()),
    LONG_ARRAY(arrayOf(LongArray::class.java), LongArrayType()),
    FLOAT_ARRAY(arrayOf(FloatArray::class.java), FloatArrayType()),
    DOUBLE_ARRAY(arrayOf(DoubleArray::class.java), DoubleArrayType()),
    BOOLEAN_ARRAY(arrayOf(BooleanArray::class.java), BooleanArrayType()),
    CHAR_ARRAY(arrayOf(CharArray::class.java), CharArrayType()),

    // 8个包装类型的数组
    BOX_BYTE_ARRAY(arrayOf(Array<Byte>::class.java), BoxByteArrayType()),
    BOX_SHORT_ARRAY(arrayOf(Array<Short>::class.java), BoxShortArrayType()),
    BOX_INT_ARRAY(arrayOf(Array<Int>::class.java), BoxIntArrayType()),
    BOX_LONG_ARRAY(arrayOf(Array<Long>::class.java), BoxLongArrayType()),
    BOX_FLOAT_ARRAY(arrayOf(Array<Float>::class.java), BoxFloatArrayType()),
    BOX_DOUBLE_ARRAY(arrayOf(Array<Double>::class.java), BoxDoubleArrayType()),
    BOX_BOOLEAN_ARRAY(arrayOf(Array<Boolean>::class.java), BoxBooleanArrayType()),
    BOX_CHAR_ARRAY(arrayOf(Array<Char>::class.java), BoxCharArrayType()),

    // String
    STRING(arrayOf(String::class.java), StringType()),
    STRING_ARRAY(arrayOf(Array<String>::class.java), StringArrayType()),

    // CharSequence
    CHAR_SEQUENCE(arrayOf(CharSequence::class.java), CharSequenceType()),
    CHAR_SEQUENCE_ARRAY(arrayOf(Array<CharSequence>::class.java), CharSequenceArrayType()),

    // Parcelable
    PARCELABLE(arrayOf(Parcelable::class.java), ParcelableType()),
    PARCELABLE_ARRAY(arrayOf(Parcelable::class.java), ParcelableArrayType()),

    // 集合类
    LIST(arrayOf(MutableList::class.java), ListType()),
    MAP(arrayOf(MutableMap::class.java), MapType()),

    // 回调类
    CALLBACK(arrayOf(), EmptyType());


    companion object {

        /**
         * 获取类型标识
         *
         * @param classType Class
         * @return
         */
        fun getTypeIndex(classType: Class<*>): Int {
            val size = values().size
            for (index in 0 until size) {
                if (values()[index].classType.contains(classType)) {
                    return index
                }
            }
            if (Parcelable::class.java.isAssignableFrom(classType)) {
                return PARCELABLE.ordinal
            }
            if (Array<Parcelable>::class.java.isAssignableFrom(classType)) {
                return PARCELABLE_ARRAY.ordinal
            }
            if (MutableList::class.java.isAssignableFrom(classType)) {
                return LIST.ordinal
            }
            if (MutableMap::class.java.isAssignableFrom(classType)) {
                return MAP.ordinal
            }
            return EMPTY.ordinal
        }

        /**
         * 根据类型标识获取 TypeIO 对象
         *
         * @param index 标识
         * @return
         */
        fun getTypeIO(index: Int): TypeIO<*> {
            return values()[index].io
        }

        /**
         * 根据类型标识，判断是否是数组类型
         *
         * @param type
         * @return
         */
        fun isArrayType(type: Int): Boolean {
            return (type >= BYTE_ARRAY.ordinal && type <= BOX_CHAR_ARRAY.ordinal)
                    || type == STRING_ARRAY.ordinal
                    || type == CHAR_SEQUENCE_ARRAY.ordinal
        }
    }
}