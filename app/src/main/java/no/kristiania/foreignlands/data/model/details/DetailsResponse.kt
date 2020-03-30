package no.kristiania.foreignlands.data.model.details


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import no.kristiania.foreignlands.data.model.details.Place

@SuppressLint("ParcelCreator")
@Parcelize
data class DetailsResponse(
    @SerializedName("place")
    val place: Place
) : Parcelable