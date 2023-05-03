package com.example.banderaweather.presentation.first

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banderaweather.R
import com.example.banderaweather.data.remote.model.CityEnum
import com.example.banderaweather.data.remote.model.TodayListApiModel
import com.example.banderaweather.databinding.FragmentFirstBinding
import com.example.newnews.presentation.models.WeatherItem
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {
    private lateinit var binding: FragmentFirstBinding
    private var adapter = GroupieAdapter()
    private lateinit var todayWeather: TodayListApiModel

//    val vm: SavedStateViewModel by viewModels<WeatherViewModel>()
    private val viewModel by viewModels<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
//        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
//
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        Log.d("asd", "asdasd")

        val cities = enumValues<CityEnum>().map { it.name }
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cities)
        spinnerAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCity = parent?.getItemAtPosition(position).toString()
                // Do something with the selected city, such as update the weather information for that city
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
        binding.spinner.adapter = spinnerAdapter

        viewModel.weatherFiveLiveData.observe(viewLifecycleOwner, Observer { it ->
            val itemList = it.DailyForecasts.map{ WeatherItem(it) }
            adapter.updateAsync(itemList)
        })

        viewModel.weatherTodayLiveData.observe(viewLifecycleOwner, Observer { it ->
            todayWeather = it[0]
            binding.todayDegree.text = todayWeather.Temperature.Metric.Value.toString() + "°" + todayWeather.Temperature.Metric.Unit
            binding.todayDescription.text = todayWeather.WeatherText
            binding.todayFeels.text = "Feels like " + todayWeather.RealFeelTemperature.Metric.Value.toString() + "°" + todayWeather.RealFeelTemperature.Metric.Unit
        })

        viewModel.getFiveDailyWeather(CityEnum.KYIV.cityKey)
        viewModel.getTodayWeather(CityEnum.KYIV.cityKey)

    }

    private fun setupRecyclerView(){
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.setOnItemClickListener { item, view ->
            val item = item as WeatherItem
            val dayDegree = item.weather.Temperature.Maximum.Value.toString() + "°" + item.weather.Temperature.Maximum.Unit
            val windSpeed = item.weather.Day.Wind.Speed.Value.toString() + " " + item.weather.Day.Wind.Speed.Unit
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                dayName = item.weather.Date, dayDegree = dayDegree, dayIcon = item.weather.Day.Icon,
                rainProbability = item.weather.Day.RainProbability, windSpeed = windSpeed,
                longPhrase = item.weather.Day.LongPhrase, humidity = item.weather.Day.Evapotranspiration.Value,
                vuIndex = item.weather.AirAndPollen.last().Value, sunrise = item.weather.Sun.Rise,
                sunset = item.weather.Sun.Set
            )
            findNavController().navigate(action)
        }
    }

}