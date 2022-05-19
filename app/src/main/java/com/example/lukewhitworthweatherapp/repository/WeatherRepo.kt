package com.example.lukewhitworthweatherapp.repository

import com.example.lukewhitworthweatherapp.api.ApiService
import com.example.lukewhitworthweatherapp.model.WeatherResponse

interface WeatherRepo {
    // update to take in city as variable
    suspend fun getForecast() : WeatherResponse
}

class WeatherRepoImpl (private val service: ApiService = ApiService.getApiService()): WeatherRepo{
    override suspend fun getForecast(): WeatherResponse {
        val response = service.getForecast()

        if (response.isSuccessful) {
            return response.body()!!
        }
        return WeatherResponse(emptyList())
    }
}