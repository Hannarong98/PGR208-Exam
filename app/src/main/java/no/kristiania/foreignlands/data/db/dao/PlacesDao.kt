package no.kristiania.foreignlands.data.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import no.kristiania.foreignlands.data.db.model.overviews.Places
import no.kristiania.foreignlands.data.db.model.overviews.Properties

@Dao
interface PlacesDao {

    @Query("SELECT * FROM places")
    suspend fun getLocalPlaces(): MutableList<Places>

    @Query("SELECT * FROM places WHERE property_id = :id")
    suspend fun getLocalPlaceById(id: String): Properties

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(places: Places)
}