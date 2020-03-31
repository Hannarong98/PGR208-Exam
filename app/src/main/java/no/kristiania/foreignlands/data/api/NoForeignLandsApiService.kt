package no.kristiania.foreignlands.data.api


import no.kristiania.foreignlands.data.model.overviews.OverviewResponse
import no.kristiania.foreignlands.data.model.details.DetailsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NoForeignLandsApiService {
    @GET("/home/api/v1/places")
    suspend fun getOverviews(): Response<OverviewResponse>

    @GET("home/api/v1/place")
    suspend fun getDetail(@Query("id") id: String): Response<DetailsResponse>

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