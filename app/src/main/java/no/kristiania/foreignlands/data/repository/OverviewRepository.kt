package no.kristiania.foreignlands.data.repository

import android.util.Log.i
import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.db.dao.PlacesDao
import no.kristiania.foreignlands.data.utils.SafeApiRequest
import no.kristiania.foreignlands.data.db.model.overviews.Places

class OverviewRepository(private val api: NoForeignLandsApiService, private val dao:PlacesDao) : SafeApiRequest() {

    suspend fun getPlaces() : MutableList<Places>? {
        //Checking row count should be more efficient than checking list size
        return if(dao.getRowCount() > 0){
            i("Repository", "Fetching local data... row count: " + dao.getRowCount().toString())
            dao.fetchLocal()
        } else {
            i("Repository", "No data in local storage, fetching from remote")
            val response = apiRequest { api.fetchRemote() }
            i("Repository", "Persisting remote data to local storage")
            dao.upsert(response?.features!!.toMutableList())
            response.features
        }
    }

}