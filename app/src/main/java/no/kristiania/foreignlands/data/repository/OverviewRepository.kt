package no.kristiania.foreignlands.data.repository

import android.util.Log.e
import android.util.Log.i
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.db.dao.PlacesDao
import no.kristiania.foreignlands.data.db.model.overviews.Places
import no.kristiania.foreignlands.data.utils.NoNetworkException
import no.kristiania.foreignlands.data.utils.SafeApiRequest

class OverviewRepository(private val api: NoForeignLandsApiService, private val dao: PlacesDao) :
    SafeApiRequest() {


    suspend fun getPlaces(): List<Places> {
        //Checking row count should be more efficient than checking list size
        try {
            return if (dao.getRowCount() > 0) {

                i("Repository", "Fetching local data... row count: ${dao.getRowCount()}")

                // Time in seconds
                val sinceLast =
                    (System.currentTimeMillis() / 1000L) - dao.getModifiedAtTimeStamp().toLong()
                if (sinceLast >= 10800) {
                    // If three hours passed since we last started the app, then check for new data
                    // This is not the most elegant way to check for new data
                    // But it should be good enough
                    checkForNewData()
                }

                dao.fetchLocal()
            } else {
                i("Repository", "No data in local storage, trying to fetching from remote")
                val response = apiRequest { api.fetchRemote() }
                dao.upsert(response?.features!!.toList())
                i("Repository", "Persisted ${response.features.size} elements to local storage")
                response.features
            }
        } catch (ex: NoNetworkException) {
            e("Repository", "No connection")
        }
        //Just return empty list if other event does not trigger
        return mutableListOf()
    }

    private suspend fun checkForNewData() {
        i("Repository", "Checking for new data")
        withContext(Dispatchers.IO) {
            val data = apiRequest { api.fetchRemote() }
            val dataSize = data?.features!!.size
            if (dao.getRowCount() < dataSize) {
                i("Repository", "New data detected: ${dataSize - dao.getRowCount()} elements found")
                dao.upsert(data.features.toMutableList())
                dao.updateModifiedTimeStamp((System.currentTimeMillis() / 1000).toString())
            } else {
                i("Repository", "Local is one to one with remote")
            }
        }
    }

}