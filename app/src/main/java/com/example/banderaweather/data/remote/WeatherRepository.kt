package com.example.banderaweather.data.remote

import com.example.banderaweather.data.remote.model.TodayListApiModel
import com.example.banderaweather.data.remote.model.TodayWeatherApiModel
import com.example.banderaweather.data.remote.model.WeatherFiveDaysApiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Named


class WeatherRepository @Inject constructor(
    private val weatherRemoteDataSource: WeatherApiInterface,
    @Named("cacheDurationInMillis") private val cacheDurationInMillis: Long
) {
    private var cachedFiveDaysWeather: WeatherFiveDaysApiModel? = null
    private var cachedTodayWeather: List<TodayListApiModel>? = null
    private var lastCacheTime: Long = 0

    suspend fun getFiveDailyWeather(
        cityKey: String,
        refresh: Boolean = false
    ): WeatherFiveDaysApiModel {
        val currentTime = System.currentTimeMillis()
        if (!refresh && cachedFiveDaysWeather != null && currentTime - lastCacheTime < cacheDurationInMillis) {
            // Return cached data if not expired and refresh is not requested
            return cachedFiveDaysWeather!!
        }
        // If cache expired or refresh is requested, make an API call and update cache
        val response = weatherRemoteDataSource.getFiveDailyWeather(cityKey)
        cachedFiveDaysWeather = response
        lastCacheTime = currentTime
        return response
    }

    suspend fun getTodayWeather(
        cityKey: String,
        refresh: Boolean = false
    ): List<TodayListApiModel> {
        val currentTime = System.currentTimeMillis()
        if (!refresh && cachedTodayWeather != null && currentTime - lastCacheTime < cacheDurationInMillis) {
            // Return cached data if not expired and refresh is not requested
            return cachedTodayWeather!!
        }
        // If cache expired or refresh is requested, make an API call and update cache
        val response = weatherRemoteDataSource.getTodayWeather(cityKey)
        cachedTodayWeather = response
        lastCacheTime = currentTime
        return response
    }
}

//package com.example.banderaweather.data.remote
//
//import com.example.banderaweather.data.remote.model.WeatherApiModel
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.withContext
//
//class WeatherRepository(
//    private val api: WeatherApi,
//    private val cache: WeatherCache,
//    private val ioDispatcher: CoroutineDispatcher
//) {
//
//    suspend fun getFiveDailyWeather(cityKey: String): WeatherApiModel {
//        // First, check the cache
//        val cachedResult = cache.get(cityKey)
//        if (cachedResult != null) {
//            return cachedResult
//        }
//
//        // If not in cache, make the API call
//        val apiResult = withContext(ioDispatcher) {
//            api.getFiveDailyWeather(cityKey)
//        }
//
//        // Cache the API result
//        cache.put(cityKey, apiResult)
//
//        return apiResult
//    }
//
//}
//
//class WeatherCache {
//    private val cache: MutableMap<String, WeatherApiModel> = mutableMapOf()
//
//    fun get(cityKey: String): WeatherApiModel? {
//        return cache[cityKey]
//    }
//
//    fun put(cityKey: String, weather: WeatherApiModel) {
//        cache[cityKey] = weather
//    }
//}
