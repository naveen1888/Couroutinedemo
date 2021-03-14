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

    private val _pokemon: MutableLiveData<Resource<PokeResponse>> = MutableLiveData()
    val pokemon: LiveData<Resource<PokeResponse>>
        get() = _pokemon

    init {
        getPokeMon()
        getWeather()
    }

//    private suspend fun demo() {
//        val one = withContext(Dispatchers.IO) { getWeather("Las Vegas") }
//        val two = withContext(Dispatchers.IO) { getPokeMon() }
//
//    }

    private fun getWeather() = viewModelScope.launch {
        _weather.value = Resource.Loading
        _weather.value = repository.getWeather("Las Vegas")
    }

    private fun getPokeMon() = viewModelScope.launch {
        _pokemon.value = Resource.Loading
       // _pokemon.value = repository.getPokeMon(10, 10)
    }

}