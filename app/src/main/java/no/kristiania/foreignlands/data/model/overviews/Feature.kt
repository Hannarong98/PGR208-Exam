package no.kristiania.foreignlands.data.model.overviews


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Feature(
    val geometry: Geometry,
    val properties: Properties
) : Parcelable