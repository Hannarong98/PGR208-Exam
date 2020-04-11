package no.kristiania.foreignlands.data.db.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import no.kristiania.foreignlands.data.db.model.overviews.Places
@Dao
interface PlacesDao {

    @Query("SELECT * FROM PLACES")
    suspend fun fetchLocal(): MutableList<Places>

    @Query("SELECT COUNT(name) from PLACES")
    suspend fun getRowCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsert(places: MutableList<Places>)
}