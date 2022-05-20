package com.example.lukewhitworthweatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lukewhitworthweatherapp.R
import com.example.lukewhitworthweatherapp.databinding.FragmentScrollBinding
import com.example.lukewhitworthweatherapp.model.WeatherListItem
import com.example.lukewhitworthweatherapp.repository.WeatherRepoImpl
import com.example.lukewhitworthweatherapp.viewmodel.WeatherViewModel

const val ERR_MSG = "Error: Network Call Failed"

class ScrollFragment(private val city: String?, private var unit: String?) : Fragment() {
    private var _binding: FragmentScrollBinding? = null
    private val binding: FragmentScrollBinding get() = _binding!!


    private lateinit var weatherRecyclerAdapter: WeatherRecyclerAdapter

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
    ): View {
        _binding = FragmentScrollBinding.inflate(layoutInflater)

        // just print the city name in the title bar
        // eg. just "London" if user inputs "london,uk"
        val cityTitle = city?.split(",")?.get(0)
        binding.tvCityTitle.text = cityTitle?.replaceFirstChar(Char::titlecase)

        unit = when(unit) {
            "°C" -> "metric"
            "°F" -> "imperial"
            else -> "default"  // kelvin is the default return value
        }

        viewModel.getForecast(city, unit)
        configureObserver()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configureObserver() {
        weatherRecyclerAdapter = WeatherRecyclerAdapter(openDetails = ::openDetails)
        viewModel.forecast.observe(viewLifecycleOwner) { response ->
            if (response.list.isEmpty()) {
                binding.tvErrorText.text = ERR_MSG
            } else {
                weatherRecyclerAdapter.setForecast(response.list)
                binding.rvForecastList.adapter = weatherRecyclerAdapter
                binding.pbLoading.visibility = View.GONE

            }
        }
    }

    private fun openDetails(weatherListItem: WeatherListItem) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_view, DetailsFragment.newInstance(weatherListItem))
            .addToBackStack(null)
            .commit()
    }
}