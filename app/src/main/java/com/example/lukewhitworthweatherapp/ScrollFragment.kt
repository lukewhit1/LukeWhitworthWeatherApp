package com.example.lukewhitworthweatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lukewhitworthweatherapp.databinding.ActivityMainBinding
import com.example.lukewhitworthweatherapp.databinding.FragmentScrollBinding
import com.example.lukewhitworthweatherapp.repository.WeatherRepoImpl
import com.example.lukewhitworthweatherapp.view.WeatherRecyclerAdapter
import com.example.lukewhitworthweatherapp.viewmodel.WeatherViewModel

class ScrollFragment : Fragment() {
    private var _binding: FragmentScrollBinding? = null
    private val binding: FragmentScrollBinding get() = _binding!!

    lateinit var weatherRecyclerAdapter: WeatherRecyclerAdapter

    private val viewModel: WeatherViewModel by lazy {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WeatherViewModel(WeatherRepoImpl()) as T
            }
        }.create(WeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScrollBinding.inflate(layoutInflater)

        weatherRecyclerAdapter = WeatherRecyclerAdapter()
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.forecast.observe(viewLifecycleOwner) { response ->
            if (response.list.isEmpty()) {
                binding.tvErrorText.text = "network call failed"
            } else {
                weatherRecyclerAdapter.setForecast(response.list)
                binding.rvForecastList.adapter = weatherRecyclerAdapter
                binding.pbLoading.visibility = View.GONE
            }
        }
    }
}