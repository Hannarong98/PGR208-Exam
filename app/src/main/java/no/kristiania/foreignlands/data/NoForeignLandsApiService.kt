package no.kristiania.foreignlands.data


import no.kristiania.foreignlands.data.response.ApiResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NoForeignLandsApiService {
    @GET("/home/api/v1/places")
    suspend fun getPlaces(): Response<ApiResponse>

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