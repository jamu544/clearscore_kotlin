package android.com.jumpco.io.myapplication.model

import android.os.Parcel
import android.os.Parcelable

class ClearScoreDetails(
    var score:Int,
    var maxScore:Int,
    var personaType: String?,
    var clientRef: String?,
    var status: String?,
    var dashboardStatus: String?,

    ): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),

        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(score)
        parcel.writeInt(maxScore)
        parcel.writeString(personaType)
        parcel.writeString(clientRef)
        parcel.writeString(status)
        parcel.writeString(dashboardStatus)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClearScoreDetails> {
        override fun createFromParcel(parcel: Parcel): ClearScoreDetails {
            return ClearScoreDetails(parcel)
        }

        override fun newArray(size: Int): Array<ClearScoreDetails?> {
            return arrayOfNulls(size)
        }
    }

}