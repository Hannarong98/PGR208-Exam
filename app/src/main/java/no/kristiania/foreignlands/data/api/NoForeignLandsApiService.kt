package no.kristiania.foreignlands.data.api


import no.kristiania.foreignlands.data.model.overviews.OverviewResponse
import no.kristiania.foreignlands.data.model.details.DetailsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NoForeignLandsApiService {
    @GET("/home/api/v1/places")
    suspend fun getOverviews(): Response<OverviewResponse>

    @GET("home/api/v1/place?id=4776700298657792")
    suspend fun getDetail(): Response<DetailsResponse>

    companion object {
        operator fun invoke() : NoForeignLandsApiService {
           return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.noforeignland.com")
                .build()
                .create(NoForeignLandsApiService::class.java)
        }
    }
}