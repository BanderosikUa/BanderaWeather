package com.example.banderaweather.data.remote

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