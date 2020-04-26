package no.kristiania.foreignlands.data.db.model.details


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class DetailsResponse(
    @SerializedName("place")
    val place: PlaceDetail
) : Parcelable