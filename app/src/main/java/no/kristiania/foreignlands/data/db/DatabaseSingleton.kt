package no.kristiania.foreignlands.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import no.kristiania.foreignlands.data.db.dao.PropertyDao
import no.kristiania.foreignlands.data.model.overviews.Properties

@Database(entities = [Properties::class], version = 1)
abstract class DatabaseSingleton : RoomDatabase() {

    abstract fun getPropertyDao(): PropertyDao

    companion object {

        @Volatile
        private var instance: DatabaseSingleton? = null
        private val LOCK = Any()

        operator fun invoke(ctx: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDb(ctx).also {
                instance = it
            }
        }

        private fun buildDb(ctx: Context) =
            Room.databaseBuilder(
                ctx.applicationContext,
                DatabaseSingleton::class.java,
                "MyDb.db"
            ).build()
    }

}