package com.example.banderaweather.data.remote.model

enum class IconApiEnum(val intValue: Int, val stringValue: String) {
    Sunny(1, "sunny"),
    MostlySunny(2, "mostlysunny"),
    PartlySunny(3, "mostlysunny"),
    IntermittentClouds(4, "intermittentclouds"),
    HazySunshine(5, "intermittentclouds"),
    MostlyCloudy(6, "intermittentclouds"),
    Cloudy(7, "cloudy"),
    Dreary(8, "cloudy"),
    Fog(11, "cloud_mist"),
    Showers(12, "cloud_rain"),
    MostlyCloudShowers(13, "cloud_rain_sun"),
    PartlyCloudShowers(14, "cloud_rain_sun"),
    ThunderStorms(15, "rain_thunder"),
    MostlyCloudyTStorms(16, "rain_thunder"),
    PartlyCloudyTStorms(17, "rain_thunder"),
    Rain(18, "shower"),
    Flurries(19, "cloudy"),
    MostlyCloudFlurries(20, "intermittentclouds"),
    PartlyCloudFlurries(21, "intermittentclouds"),
    Snow(22, "snow"),
    CloudSnow(23, "cloud_snow"),
    Ice(24, "cloud_snow"),
    Sleet(25, "cloud_mist"),
    FreezingRain(26, "cloud_rain"),
    RainSnow(27, "cloud_snow_wind"),
    Night(33, "moon"),
    MostlyClearCloudyNight(34, "moon_small_cloud"),
    PartlyCloudyNight(35, "moon_small_cloud"),
    IntermittentCloudyNight(36, "moon_big_cloud"),
    HazyCloudyNight(37, "moon_big_cloud"),
    MostlyCloudyNight(38, "moon_big_cloud"),
    PartlyCloudyShowerNight(39, "moon_shower"),
    MostlyCloudyShowerNight(40, "moon_shower"),
    PartlyCloudyShowerNightThunder(41, "moon_shower"),
    MostlyCloudyShowerNightThunder(42, "moon_shower"),
    MostlyCloudyWithFlurries(43, "moon_big_cloud"),
    MostlyCloudyWithSnow(44, "moon_big_cloud");


    companion object {
        fun getStringFromInt(intValue: Int): String {
            return values().find { it.intValue == intValue }?.stringValue ?: "unknown"
        }
    }
}