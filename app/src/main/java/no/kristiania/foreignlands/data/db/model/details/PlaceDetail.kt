package no.kristiania.foreignlands.data.db.model.details


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class PlaceDetail(
    @SerializedName("banner")
    val banner: String,
    @SerializedName("comments")
    val comments: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("lat")
    val lon: Double,
    @SerializedName("lon")
    val lat: Double,
    @SerializedName("name")
    val name: String
) : Parcelable