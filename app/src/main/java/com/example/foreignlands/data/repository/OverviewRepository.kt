package com.example.foreignlands.data.repository

import com.example.foreignlands.data.NoForeignLandsApiService
import com.example.foreignlands.data.SafeApiRequest

class OverviewRepository(private val api: NoForeignLandsApiService) : SafeApiRequest() {
    suspend fun getPlaces() = apiRequest{api.getPlaces()}
}