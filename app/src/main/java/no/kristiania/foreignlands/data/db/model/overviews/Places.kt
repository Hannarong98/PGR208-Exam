package no.kristiania.foreignlands.data.db.model.overviews


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.*
import no.kristiania.foreignlands.data.db.utils.ListTypeConverter

@SuppressLint("ParcelCreator")
@Parcelize
@Entity
data class Places(
    @PrimaryKey(autoGenerate = true)
    @Embedded
    val id: Long,
    @Embedded
    val geometry: Geometry,
    @Embedded
    val properties: Properties
) : Parcelable