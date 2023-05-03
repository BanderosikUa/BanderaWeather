package com.example.banderaweather.data.remote.model

// Temperature type, speed type, etc

data class ValueApiModel (
    val Value: Float,
    val Unit: String,
    val Phrase: String?,
)