package no.kristiania.foreignlands.data.db.model.overviews


import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Entity
data class Places(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "table_id")
    val id: Long,
    @Embedded
    val geometry: Geometry,
    @Embedded
    val properties: Properties,
    @ColumnInfo(defaultValue = "(strftime('%s','now'))")
    val timestampSeconds: String
) : Parcelable