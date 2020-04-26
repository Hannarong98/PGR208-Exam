package no.kristiania.foreignlands.data.db.model.overviews


import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class OverviewResponse(
    val features: MutableList<Places>
) : Parcelable