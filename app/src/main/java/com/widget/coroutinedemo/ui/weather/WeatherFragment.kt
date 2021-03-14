package com.widget.coroutinedemo.ui.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.widget.coroutinedemo.data.network.Resource
import com.widget.coroutinedemo.data.network.WeatherApi
import com.widget.coroutinedemo.data.repository.WeatherRepository
import com.widget.coroutinedemo.data.responses.weather.Weather
import com.widget.coroutinedemo.data.responses.weather.WeatherResponse
import com.widget.coroutinedemo.databinding.FragmentWeatherBinding
import com.widget.coroutinedemo.ui.base.BaseFragment
import com.widget.coroutinedemo.ui.visible

class WeatherFragment :
    BaseFragment<WeatherViewModel, FragmentWeatherBinding, WeatherRepository>() {

    companion object {
        const val TAG: String = "WeatherFragment"
    }

    private var weatherList = mutableListOf<Weather>()
    private lateinit var adapter: WeatherAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let {
            Log.e(TAG, "Not null")
        }
        initView()
        observeWeather()
    }

    private fun initView() {
        with(binding) {
            recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = WeatherAdapter(weatherList)
            recyclerView.adapter = adapter
        }
    }

        override fun getViewModel() = WeatherViewModel::class.java

        override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
        ) = FragmentWeatherBinding.inflate(inflater, container, false)


        override fun getFragmentRepository(): WeatherRepository {
            val api = remoteDataSource.buildApi(WeatherApi::class.java)
            return WeatherRepository(api)
        }

        private fun observeWeather() {
            viewModel.weather.observe(viewLifecycleOwner, {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressbar.visible(true)
                    }
                    is Resource.Success -> {
                        binding.progressbar.visible(false)
                        updateView(it.value);
                    }
                    is Resource.Failure -> {
                        binding.progressbar.visible(false)
                        Log.e(TAG, it.errorBody.toString())
                    }
                }
            })
        }

        private fun updateView(value: WeatherResponse) {
            value.let {
                return@let weatherList.addAll(it.weather).apply {
                    adapter.notifyDataSetChanged()
                }
            }
        }


    }