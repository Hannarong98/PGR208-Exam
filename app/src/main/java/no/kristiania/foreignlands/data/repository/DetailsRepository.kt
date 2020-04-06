package no.kristiania.foreignlands.data.repository

import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.model.details.Place
import no.kristiania.foreignlands.data.utils.SafeApiRequest

class DetailsRepository(private val api: NoForeignLandsApiService) : SafeApiRequest() {

    suspend fun getPlaceDetails(id: String): Place? {
        val detailResponse = apiRequest { api.getDetail(id) }
        return detailResponse?.place
    }

}