package no.kristiania.foreignlands.data.db.model.overviews


import android.annotation.SuppressLint
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import no.kristiania.foreignlands.data.db.utils.ListTypeConverter

@SuppressLint("ParcelCreator")
@Parcelize
data class Geometry(
    val coordinates: List<Double>
) : Parcelable