package com.example.foreignlands.data


import com.example.foreignlands.data.response.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface NoForeignLandsApiService {
    @GET("/home/api/v1/places")
    fun getPlaces(): Call<ApiResponse>
}