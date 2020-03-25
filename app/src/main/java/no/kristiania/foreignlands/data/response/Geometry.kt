package no.kristiania.foreignlands.data.response


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Geometry(
    val coordinates: List<Double>
) : Parcelable