package no.kristiania.foreignlands.data.model.details


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import no.kristiania.foreignlands.data.model.details.Image

@SuppressLint("ParcelCreator")
@Parcelize
data class Place(
    @SerializedName("banner")
    val banner: String,
    @SerializedName("comments")
    val comments: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("name")
    val name: String
) : Parcelable