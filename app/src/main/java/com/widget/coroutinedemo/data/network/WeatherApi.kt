package com.widget.coroutinedemo.data.network

import com.widget.coroutinedemo.data.responses.weather.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("2.5/weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") appID: String
    ): WeatherResponse
}
