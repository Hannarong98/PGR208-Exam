package no.kristiania.foreignlands.data.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import no.kristiania.foreignlands.data.db.model.overviews.Places
@Dao
interface PlacesDao {

    @Query("SELECT * FROM places")
    suspend fun getLocalPlaces(): MutableList<Places>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(places: Places)
}