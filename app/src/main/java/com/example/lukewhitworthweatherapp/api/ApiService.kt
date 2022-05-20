package com.example.lukewhitworthweatherapp.api

import com.example.lukewhitworthweatherapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// My API Key
const val APP_ID = "d4da297f8dec7cecf03c51cd9fff96b6"

interface ApiService {
    @GET("forecast")
    suspend fun getForecast(
        @Query("q") name: String? = null,
        @Query("appid") apiKey: String = APP_ID,
        @Query("units") units: String? = "default"
    ): Response<WeatherResponse>

    companion object {
        private var instance: ApiService? = null
        fun getApiService(): ApiService {
            if (instance ==  null) {
                instance = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return instance!!
        }
    }
}