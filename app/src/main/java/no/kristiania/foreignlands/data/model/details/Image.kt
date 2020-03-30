package no.kristiania.foreignlands.data.model.details


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Image(
    @SerializedName("caption")
    val caption: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Long,
    @SerializedName("likes")
    val likes: List<Long>,
    @SerializedName("servingUrl")
    val servingUrl: String,
    @SerializedName("uploadedByUserDisplayName")
    val uploadedByUserDisplayName: String,
    @SerializedName("uploadedByUserId")
    val uploadedByUserId: Long,
    @SerializedName("uploadedDate")
    val uploadedDate: Long,
    @SerializedName("width")
    val width: Int
) : Parcelable