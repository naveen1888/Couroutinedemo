package com.widget.coroutinedemo.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.widget.coroutinedemo.data.network.Resource
import com.widget.coroutinedemo.data.repository.WeatherRepository
import com.widget.coroutinedemo.data.responses.pokemon.PokeResponse
import com.widget.coroutinedemo.data.responses.weather.WeatherResponse
import com.widget.coroutinedemo.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : BaseViewModel(repository) {

    private val _weather: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()
    val weather: LiveData<Resource<WeatherResponse>>
        get() = _weather

    init {
        getWeather()
    }

    private fun getWeather() = viewModelScope.launch {
        _weather.value = Resource.Loading
        _weather.value = repository.getWeather("Las Vegas")
    }


}