package com.example.banderaweather.data.remote

import com.example.banderaweather.data.remote.model.WeatherApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import retrofit2.http.Path

//class WeatherApi(
//    private val ApiInterface: WeatherApiInterface,
//    private val ioDispatcher: CoroutineDispatcher
//){
//
//
//    suspend fun getFiveDailyWeather(cityKey: String): WeatherApiModel =
//        withContext(ioDispatcher){
//            ApiFactory.weatherApi.getFiveDailyWeather(cityKey)
//        }
//
//    interface WeatherApiInterface {
//
//        @GET("forecasts/v1/daily/5day/{cityKey}?apikey=N6aiUGMnry26w2tKkvmMiy35XgOTvkaE&details=true&metric=true")
//        suspend fun getFiveDailyWeather(@Path("cityKey") cityKey: String?): WeatherApiModel
//    }
//}