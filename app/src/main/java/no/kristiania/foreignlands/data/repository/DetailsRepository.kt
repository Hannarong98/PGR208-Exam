package no.kristiania.foreignlands.data.repository

import android.util.Log.e
import androidx.lifecycle.MutableLiveData
import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.utils.SafeApiRequest
import no.kristiania.foreignlands.data.model.details.Place

class DetailsRepository(private val api: NoForeignLandsApiService) : SafeApiRequest() {

    suspend fun getPlaceDetails(id: String): Place? {
        val detailResponse = apiRequest { api.getDetail(id) }
        return detailResponse?.place
    }

}