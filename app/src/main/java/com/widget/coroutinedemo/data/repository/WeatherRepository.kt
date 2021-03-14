package com.widget.coroutinedemo.data.repository

import com.widget.coroutinedemo.data.network.RemoteDataSource
import com.widget.coroutinedemo.data.network.WeatherApi

class WeatherRepository(
    private val api: WeatherApi
) : BaseRepository() {

    //suspend call for coroutine
    suspend fun getWeather(city: String) = safeApiCall {
        api.getWeather(city, RemoteDataSource.API_KEY)
    }
}