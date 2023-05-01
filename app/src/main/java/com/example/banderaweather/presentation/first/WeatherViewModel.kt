package com.example.banderaweather.presentation.first

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banderaweather.data.remote.ApiFactory
import com.example.banderaweather.data.remote.WeatherApiInterface
import com.example.banderaweather.data.remote.WeatherRepository
import com.example.banderaweather.data.remote.model.WeatherApiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
    ):ViewModel() {
    private val _weatherMutableLiveData = MutableLiveData<WeatherApiModel>()
    val weatherLiveData: LiveData<WeatherApiModel> = _weatherMutableLiveData

    fun getFiveDailyWeather(cityKey: String) {
        viewModelScope.launch {
            val response = weatherRepository.getFiveDailyWeather(cityKey)
            _weatherMutableLiveData.postValue(response)
        }
    }

}