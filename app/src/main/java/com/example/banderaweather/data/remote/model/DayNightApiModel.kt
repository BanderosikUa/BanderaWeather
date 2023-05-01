package com.example.banderaweather.data.remote.model

data class DayNightApiModel(
    val Icon: Int,
    val IconPhrase: String?,
    val LongPhrase: String?,
    val PrecipitationProbability: Int?,
    val ThunderstormProbability: Int?,
    val RainProbability: Int?,
    val Wind: WindApiModel?,


    )
