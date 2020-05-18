package no.kristiania.foreignlands.data.api


import no.kristiania.foreignlands.data.db.model.details.DetailsResponse
import no.kristiania.foreignlands.data.db.model.overviews.OverviewResponse
import no.kristiania.foreignlands.data.utils.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NoForeignLandsApiService {
    @GET("/home/api/v1/places")
    suspend fun fetchRemote(): Response<OverviewResponse>

    @GET("home/api/v1/place")
    suspend fun fetchRemoteDetail(@Query("placeId") id: String): Response<DetailsResponse>

    companion object {
        operator fun invoke(
            networkInterceptor: NetworkConnectionInterceptor
        ): NoForeignLandsApiService {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.noforeignland.com")
                .build()
                .create(NoForeignLandsApiService::class.java)
        }
    }
}
