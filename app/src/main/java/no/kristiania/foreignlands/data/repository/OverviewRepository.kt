package no.kristiania.foreignlands.data.repository

import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.utils.SafeApiRequest
import no.kristiania.foreignlands.data.model.overviews.Places

class OverviewRepository(private val api: NoForeignLandsApiService) : SafeApiRequest() {
    suspend fun getPlaces() : MutableList<Places>? {
        val placeResponse =  apiRequest{api.getOverviews()}
        return placeResponse?.features?.toMutableList()
    }
}