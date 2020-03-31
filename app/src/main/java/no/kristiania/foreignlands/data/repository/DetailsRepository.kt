package no.kristiania.foreignlands.data.repository

import android.util.Log.e
import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.utils.SafeApiRequest
import no.kristiania.foreignlands.data.model.details.Place

class DetailsRepository(private val api: NoForeignLandsApiService) : SafeApiRequest() {
    suspend fun getPlaceDetails(): Place? {
        val detailResponse = apiRequest { api.getDetail(4776700298657792) }
        e("Detail Response", detailResponse?.place.toString())
        return detailResponse?.place
    }
}