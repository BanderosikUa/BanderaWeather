package com.example.banderaweather.data.remote

import com.example.banderaweather.data.remote.model.WeatherApiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import retrofit2.http.Path
import javax.inject.Inject


class WeatherRepository @Inject constructor(
    private val weatherRemoteDataSource: WeatherApiInterface,
//    private val externalScope: CoroutineScope,
//    private val weatherMutex: Mutex = Mutex(),
//    private var weather: WeatherApiModel,
    ){
    suspend fun getFiveDailyWeather(cityKey: String, refresh: Boolean = false): WeatherApiModel {
//        return if (refresh){
//            externalScope.async {
//                weatherRemoteDataSource.getFiveDailyWeather(cityKey).also { response ->
//                    weatherMutex.withLock {
//                        weather = response
//                    }
//                }
//            }.await()
//        } else {
//            return weatherMutex.withLock { this.weather }
//        }
        return weatherRemoteDataSource.getFiveDailyWeather(cityKey)
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
