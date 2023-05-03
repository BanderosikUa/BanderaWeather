package com.example.banderaweather.data.remote.model

//@Serializable
data class WeatherFiveDaysApiModel(
    val DailyForecasts: List<DailyForecastApiModel>
)
