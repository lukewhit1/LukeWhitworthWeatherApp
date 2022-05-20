package com.example.lukewhitworthweatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lukewhitworthweatherapp.databinding.ForecastListItemBinding
import com.example.lukewhitworthweatherapp.model.WeatherListItem

class WeatherRecyclerAdapter(private val forecast: MutableList<WeatherListItem> = mutableListOf(),
                             private val openDetails: (WeatherListItem) -> Unit
                             ): RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherViewHolder>() {

        fun setForecast(newList: List<WeatherListItem>) {
            forecast.clear()
            forecast.addAll(newList)
            notifyDataSetChanged()
        }

        inner class WeatherViewHolder(private val binding: ForecastListItemBinding): RecyclerView.ViewHolder(binding.root) {
            fun bind(forecastItem: WeatherListItem) {
                Glide.with(binding.ivImage)
                    .load("https://openweathermap.org/img/wn/${forecastItem.weather[0].icon}@2x.png")
                    .into(binding.ivImage)

                // Scroll Fragment
                binding.tvTemperature.text = forecastItem.getTemp()
                binding.tvDescription.text = forecastItem.getDescription()
                binding.tvFeelsLike.text = forecastItem.getFeelsLike()

                binding.root.setOnClickListener {
                    openDetails(forecastItem)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            ForecastListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(forecast[position])
    }

    override fun getItemCount(): Int {
        return forecast.size
    }
}