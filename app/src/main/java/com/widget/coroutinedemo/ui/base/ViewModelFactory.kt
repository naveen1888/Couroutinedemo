package com.widget.coroutinedemo.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.widget.coroutinedemo.data.repository.BaseRepository
import com.widget.coroutinedemo.data.repository.WeatherRepository
import com.widget.coroutinedemo.ui.weather.WeatherViewModel

class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(WeatherViewModel::class.java) -> WeatherViewModel(repository as WeatherRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}