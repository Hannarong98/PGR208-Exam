package no.kristiania.foreignlands.data.repository

import no.kristiania.foreignlands.data.NoForeignLandsApiService
import no.kristiania.foreignlands.data.SafeApiRequest
import no.kristiania.foreignlands.data.response.Feature

class OverviewRepository(private val api: NoForeignLandsApiService) : SafeApiRequest() {
    suspend fun getPlaces() : MutableList<Feature>? {
        val placeResponse =  apiRequest{api.getPlaces()}
        return placeResponse?.features?.toMutableList()
    }
}