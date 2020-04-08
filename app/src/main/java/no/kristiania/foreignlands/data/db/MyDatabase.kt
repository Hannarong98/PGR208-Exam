package no.kristiania.foreignlands.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import no.kristiania.foreignlands.data.db.dao.PlacesDao
import no.kristiania.foreignlands.data.db.model.overviews.Places

@Database(entities = [Places::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun getPlacesDao(): PlacesDao

    companion object {

        @Volatile
        private var instance: MyDatabase? = null
        private val LOCK = Any()

        operator fun invoke(ctx: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDb(ctx).also {
                instance = it
            }
        }

        private fun buildDb(ctx: Context) =
            Room.databaseBuilder(
                ctx.applicationContext,
                MyDatabase::class.java,
                "MyDb.db"
            ).build()
    }

}