package com.example.banderaweather.presentation.first

import androidx.lifecycle.*
import com.example.banderaweather.data.remote.WeatherRepository
import com.example.banderaweather.data.remote.model.TodayListApiModel
import com.example.banderaweather.data.remote.model.TodayWeatherApiModel
import com.example.banderaweather.data.remote.model.WeatherFiveDaysApiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val state: SavedStateHandle
    ):ViewModel() {
    private val _weatherFiveMutableLiveData = MutableLiveData<WeatherFiveDaysApiModel>()
    private val _weatherTodayMutableLiveData = MutableLiveData<List<TodayListApiModel>>()
    val weatherFiveLiveData: LiveData<WeatherFiveDaysApiModel> = _weatherFiveMutableLiveData
    val weatherTodayLiveData: LiveData<List<TodayListApiModel>> = _weatherTodayMutableLiveData

    fun getFiveDailyWeather(cityKey: String) {
        viewModelScope.launch {
            val response = weatherRepository.getFiveDailyWeather(cityKey)
            _weatherFiveMutableLiveData.postValue(response)
        }
    }

    fun getTodayWeather(cityKey: String) {
        viewModelScope.launch {
            val response = weatherRepository.getTodayWeather(cityKey)
            _weatherTodayMutableLiveData.postValue(response)
        }
    }

}