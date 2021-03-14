package com.widget.coroutinedemo.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.widget.coroutinedemo.data.responses.weather.Weather
import com.widget.coroutinedemo.databinding.ItemViewWeatherBinding

class WeatherAdapter(private val list: List<Weather>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewWeatherBinding.inflate(inflater)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size

    inner class WeatherViewHolder(private val binding: ItemViewWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Weather) {
            with(binding) {
                itemLabel.text = item.description
            }
        }

    }
}