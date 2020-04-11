package no.kristiania.foreignlands.data.db.model.overviews


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity

@SuppressLint("ParcelCreator")
@Parcelize
data class Places(
    val geometry: Geometry,
    val properties: Properties
) : Parcelable