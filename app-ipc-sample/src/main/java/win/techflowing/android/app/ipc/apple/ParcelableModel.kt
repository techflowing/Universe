package win.techflowing.android.app.ipc.apple

import android.os.Parcel
import android.os.Parcelable
import win.techflowing.android.ipc.IParcelableObject

/**
 * Parcelable 数据
 *
 * @author techflowing@gmail.com
 * @since 2022/11/19 23:31
 */
class ParcelableModel() : IParcelableObject {

    private var name: String? = null
    private var age: Int = 0

    constructor(name: String?, age: Int) : this() {
        this.name = name
        this.age = age
    }

    constructor(parcel: Parcel) : this() {
        readFromParcel(parcel)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    override fun readFromParcel(parcel: Parcel) {
        this.name = parcel.readString()
        this.age = parcel.readInt()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "name：$name, age: $age"
    }

    fun setAge(age: Int) {
        this.age = age
    }

    fun getAge(): Int {
        return age
    }

    companion object CREATOR : Parcelable.Creator<ParcelableModel> {
        override fun createFromParcel(parcel: Parcel): ParcelableModel {
            return ParcelableModel(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableModel?> {
            return arrayOfNulls(size)
        }
    }
}