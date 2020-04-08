package no.kristiania.foreignlands.data.db.model.overviews


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Properties(
    val id: String,
    val name: String
) : Parcelable