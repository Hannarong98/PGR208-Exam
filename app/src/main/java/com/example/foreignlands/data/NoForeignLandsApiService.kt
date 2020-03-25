package com.example.foreignlands.data


import com.example.foreignlands.data.response.ApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlin.contracts.Returns

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