package no.kristiania.foreignlands.data.repository

import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.db.model.details.PlaceDetail
import no.kristiania.foreignlands.data.utils.SafeApiRequest

class DetailsRepository(private val api: NoForeignLandsApiService) : SafeApiRequest() {

    suspend fun getRemotePlaceDetails(id: String): PlaceDetail? {
        val detailResponse = apiRequest { api.fetchRemoteDetail(id) }
        return detailResponse?.place
    }

}