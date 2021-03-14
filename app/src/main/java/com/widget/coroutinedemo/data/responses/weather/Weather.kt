package com.widget.coroutinedemo.data.responses.weather

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)