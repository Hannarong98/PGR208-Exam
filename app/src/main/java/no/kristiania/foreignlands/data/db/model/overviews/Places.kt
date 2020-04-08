package no.kristiania.foreignlands.data.db.model.overviews


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity

@SuppressLint("ParcelCreator")
@Parcelize
@Entity
data class Places(
    @Embedded(prefix = "geometry_")
    val geometry: Geometry,
    @Embedded(prefix = "property_")
    val properties: Properties
) : Parcelable