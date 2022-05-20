package com.example.lukewhitworthweatherapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*
import kotlin.math.roundToInt

data class WeatherResponse(
    val list: List<WeatherListItem>
)

@Parcelize
data class WeatherListItem(
    val coord: Coordinates,
    val weather: @RawValue List<Weather>,
    val main: Main,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Int,
    val sys: WeatherSys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
): Parcelable {
    fun getDescription(): String {
        return this.weather[0].main.capitalizeWords()
    }
    fun getDetailedDescription(): String {
        return this.weather[0].description.capitalizeWords()
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

@Parcelize
data class Coordinates(
    val lon: Double,
    val lat: Double
): Parcelable

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

@Parcelize
data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
): Parcelable

@Parcelize
data class Wind(
    val speed: Double,
    val deg: Int
): Parcelable

@Parcelize
data class Clouds(
    val all: Int
): Parcelable

@Parcelize
data class WeatherSys(
    val type: Int,
    val id: Int,
    val message: Double,
    val Country: String,
    val sunrise: Int,
    val sunset: Int
): Parcelable