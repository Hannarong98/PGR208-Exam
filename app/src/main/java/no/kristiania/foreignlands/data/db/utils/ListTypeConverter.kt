package no.kristiania.foreignlands.data.db.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object ListTypeConverter {

    @JvmStatic
    @TypeConverter
    fun fromStringToList(value: String): List<Double> {
        val stringType = object : TypeToken<List<Double>>() {}.type
        return Gson().fromJson(value, stringType)
    }

    @JvmStatic
    @TypeConverter
    fun toList(value: List<Double>): String {
        val listType = object : TypeToken<List<Double>>() {}.type
        return Gson().toJson(value, listType)
    }
}