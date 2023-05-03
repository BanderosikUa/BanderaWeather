package com.example.banderaweather.data.remote.model

data class TodayListApiModel(
    val WeatherIcon: Int,
    val WeatherText: String?,
    val Temperature: WeatherMetricApiModel,
    val RealFeelTemperature: WeatherMetricApiModel,

)
