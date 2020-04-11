package no.kristiania.foreignlands.data.repository

import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.db.model.details.PlaceDetail
import no.kristiania.foreignlands.data.utils.SafeApiRequest

class DetailsRepository(private val api: NoForeignLandsApiService) : SafeApiRequest() {
    // This function could've been together with the over repository class
    // But for the sake of high cohesion i kept it separated
    suspend fun getRemotePlaceDetails(id: String): PlaceDetail? {
        val detailResponse = apiRequest { api.fetchRemoteDetail(id) }
        return detailResponse?.place
    }

}