package no.kristiania.foreignlands.data.db.model.details


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class DetailsResponse(
    @SerializedName("place")
    val place: PlaceDetail
) : Parcelable