package no.kristiania.foreignlands.data.model.overviews


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import no.kristiania.foreignlands.data.model.overviews.Feature

@SuppressLint("ParcelCreator")
@Parcelize
data class OverviewResponse(
    val features: MutableList<Feature>
) : Parcelable