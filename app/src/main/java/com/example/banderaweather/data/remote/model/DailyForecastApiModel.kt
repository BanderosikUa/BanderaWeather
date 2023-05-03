package com.example.banderaweather.data.remote.model

data class DailyForecastApiModel(
    val Date: String,
    val Sun: SunMoonApiModel,
    val Moon: SunMoonApiModel,
    val Temperature: TemperatureApiModel,
    val RealFeelTemperature: TemperatureApiModel,
    val AirAndPollen: List<CategoryApiModel>,
    val Day: DayNightApiModel,
    val Night: DayNightApiModel
)
