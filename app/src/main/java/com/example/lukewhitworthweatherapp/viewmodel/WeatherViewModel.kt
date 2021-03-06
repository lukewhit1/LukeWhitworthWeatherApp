package com.example.lukewhitworthweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lukewhitworthweatherapp.model.WeatherResponse
import com.example.lukewhitworthweatherapp.repository.WeatherRepoImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val repoImpl: WeatherRepoImpl): ViewModel() {
//    init {
//        getForecast()
//    }

    private val _forecast = MutableLiveData<WeatherResponse>()
    val forecast: LiveData<WeatherResponse> get() = _forecast

    fun getForecast(city: String?, unit: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repoImpl.getForecast(city, unit)
            _forecast.postValue(response)
        }
    }
}