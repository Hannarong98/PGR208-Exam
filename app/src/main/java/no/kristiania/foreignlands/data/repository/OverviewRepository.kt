package no.kristiania.foreignlands.data.repository

import android.util.Log.e
import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.db.dao.PlacesDao
import no.kristiania.foreignlands.data.utils.SafeApiRequest
import no.kristiania.foreignlands.data.db.model.overviews.Places

class OverviewRepository(private val api: NoForeignLandsApiService, private val dao:PlacesDao) : SafeApiRequest() {

    suspend fun getPlaces() : MutableList<Places>? {

        //checking row count should be more efficient than checking list size
        return if(dao.getRowCount() > 0){
            e("cache", dao.getRowCount().toString())
            dao.fetchLocal()
        } else {
            e("cache", dao.getRowCount().toString())
            val response = apiRequest { api.fetchRemote() }
            dao.upsert(response?.features!!.toMutableList())
            response.features
        }

    }

}