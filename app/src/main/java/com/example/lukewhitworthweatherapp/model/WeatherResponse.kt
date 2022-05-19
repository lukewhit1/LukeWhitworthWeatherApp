package com.example.lukewhitworthweatherapp.model

import java.util.*
import kotlin.math.round
import kotlin.math.roundToInt

data class WeatherResponse(
    val list: List<WeatherListItem>
)

data class WeatherListItem(
    val coord: Coordinates,
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Int,
    val sys: WeatherSys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
) {
    fun getDescription(): String {
        return this.weather[0].main.capitalizeWords()
    }
    fun getTemp(): String {
        return "${this.main.temp.roundToInt()}°"
    }
    fun getFeelsLike(): String {
        return "Feels like: ${this.main.feels_like.roundToInt()}°"
    }
    private fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it ->
        it.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    } }

}

data class Coordinates(
    val lon: Double,
    val lat: Double
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
)

data class Wind(
    val speed: Double,
    val deg: Int
)

data class Clouds(
    val all: Int
)

data class WeatherSys(
    val type: Int,
    val id: Int,
    val message: Double,
    val Country: String,
    val sunrise: Int,
    val sunset: Int
)