package no.kristiania.foreignlands.data.db.dao


import androidx.room.*
import no.kristiania.foreignlands.data.db.model.overviews.Places
@Dao
interface PlacesDao {

    @Transaction
    @Query("SELECT * FROM PLACES")
    suspend fun fetchLocal(): MutableList<Places>

    @Query("SELECT COUNT(name) from PLACES")
    suspend fun getRowCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(places: MutableList<Places>)

    @Query("UPDATE places set timestampSeconds= :newTimeStamp")
    suspend fun updateModifiedTimeStamp(newTimeStamp: String)

    @Query("SELECT timestampSeconds from PLACES LIMIT 1")
    suspend fun getModifiedAtTimeStamp(): String
}