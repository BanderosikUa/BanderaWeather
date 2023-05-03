package com.example.newnews.presentation.models

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.banderaweather.R
import com.example.banderaweather.data.remote.model.DailyForecastApiModel
import com.example.banderaweather.data.remote.model.IconApiEnum
import com.example.banderaweather.databinding.ItemShortWeatherBinding
import com.xwray.groupie.viewbinding.BindableItem
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherItem(val weather: DailyForecastApiModel) : BindableItem<ItemShortWeatherBinding>() {
    override fun initializeViewBinding(view: View): ItemShortWeatherBinding {
        Log.d("it2", "asdaspdasd")
        return ItemShortWeatherBinding.bind(view)
    }


    override fun bind(viewBinding: ItemShortWeatherBinding, position: Int) {
        Log.d("it2", viewBinding.toString())
        setImages(viewBinding)

        viewBinding.dayTextView.text = getDayOfWeek(weather.Date)
        viewBinding.dayPrecipitation.text = weather.Day.PrecipitationProbability.toString()
        val maxTemperature = weather.Temperature.Maximum
        viewBinding.temperatureMaximum.text =
            maxTemperature.Value.toString() + "°" + maxTemperature.Unit
        val minTemperature = weather.Temperature.Minimum
        viewBinding.temperatureMinimum.text =
            minTemperature.Value.toString() + "°" + minTemperature.Unit
    }

    override fun getLayout(): Int {
        return R.layout.item_short_weather
    }

    private fun getDayOfWeek(dateString: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(dateString) ?: return ""
        val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        return dayOfWeekFormat.format(date)
    }

    private fun setImages(viewBinding: ItemShortWeatherBinding){
        val dayImageView = viewBinding.weatherDayImageView
        val dayResourceId = dayImageView.context.resources.getIdentifier(
            IconApiEnum.getStringFromInt(weather.Day.Icon),
            "drawable",
            dayImageView.context.packageName
        )
        Glide.with(dayImageView.context)
            .load(dayResourceId)
            .into(dayImageView)

        val nightImageView = viewBinding.weatherNoonImageView
        val nightResourceId = nightImageView.context.resources.getIdentifier(
            IconApiEnum.getStringFromInt(weather.Night.Icon),
            "drawable",
            nightImageView.context.packageName
        )
        Glide.with(nightImageView.context)
            .load(nightResourceId)
            .into(nightImageView)
    }
}