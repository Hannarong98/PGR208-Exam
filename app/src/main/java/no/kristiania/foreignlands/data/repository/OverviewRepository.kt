package no.kristiania.foreignlands.data.repository

import no.kristiania.foreignlands.data.NoForeignLandsApiService
import no.kristiania.foreignlands.data.SafeApiRequest

class OverviewRepository(private val api: NoForeignLandsApiService) : SafeApiRequest() {
    suspend fun getPlaces() = apiRequest{api.getPlaces()}
}