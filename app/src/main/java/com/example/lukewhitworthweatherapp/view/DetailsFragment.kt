package com.example.lukewhitworthweatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.lukewhitworthweatherapp.databinding.FragmentDetailsBinding
import com.example.lukewhitworthweatherapp.model.WeatherListItem

class DetailsFragment: Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding? get() = _binding

    companion object {
        const val KEY = "DetailsFragmentKey"
        fun newInstance(weatherListItem: WeatherListItem): DetailsFragment {
            val fragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY, weatherListItem)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(layoutInflater)
        val weatherListItem: WeatherListItem? = arguments?.getParcelable(KEY)

        binding?.let {
            Glide.with(it.ivDetailImage)
                .load("https://openweathermap.org/img/wn/${weatherListItem?.weather?.get(0)?.icon}@2x.png")
                .into(binding!!.ivDetailImage)
        }

        binding?.tvDetailTemp?.text = weatherListItem?.getTemp()
        binding?.tvDetailsFeelsLike?.text = weatherListItem?.getFeelsLike()
        binding?.tvDetailsDescription?.text = weatherListItem?.getDetailedDescription()
        binding?.tvDetailsWeather?.text = weatherListItem?.getDescription()

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}