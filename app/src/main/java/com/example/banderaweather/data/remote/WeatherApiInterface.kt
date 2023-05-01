package com.example.banderaweather.data.remote

import com.example.banderaweather.data.remote.model.WeatherApiModel
import com.example.banderaweather.domain.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiInterface {

    @GET("forecasts/v1/daily/5day/{cityKey}")
    suspend fun getFiveDailyWeather(
        @Path("cityKey") cityKey: String?,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("details") details: Boolean = true,
        @Query("metric") metric: Boolean = true
    ): WeatherApiModel
}