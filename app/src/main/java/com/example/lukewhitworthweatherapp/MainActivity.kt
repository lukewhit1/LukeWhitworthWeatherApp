package com.example.lukewhitworthweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lukewhitworthweatherapp.databinding.ActivityMainBinding
import com.example.lukewhitworthweatherapp.repository.WeatherRepoImpl
import com.example.lukewhitworthweatherapp.view.WeatherRecyclerAdapter
import com.example.lukewhitworthweatherapp.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    lateinit var weatherRecyclerAdapter: WeatherRecyclerAdapter

    private val viewModel:WeatherViewModel by lazy {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WeatherViewModel(WeatherRepoImpl()) as T
            }
        }.create(WeatherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherRecyclerAdapter = WeatherRecyclerAdapter()
        configureObserver()
    }

    fun configureObserver() {
        viewModel.forecast.observe(this) { response ->
            if (response.list.isEmpty()) {
                binding.tvErrorText.text = "network call failed"
            } else {
                weatherRecyclerAdapter.setForecast(response.list)
                binding.rvForecastList.adapter = weatherRecyclerAdapter
                binding.pbLoading.visibility = View.GONE
                //binding.tvErrorText.text = response.list[0].weather[0].description
            }
        }
    }
}