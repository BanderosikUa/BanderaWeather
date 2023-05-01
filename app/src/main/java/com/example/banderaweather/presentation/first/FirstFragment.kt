package com.example.banderaweather.presentation.first

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banderaweather.R
import com.example.banderaweather.data.remote.ApiFactory
import com.example.banderaweather.data.remote.WeatherApiInterface
import com.example.banderaweather.data.remote.model.CityEnum
import com.example.banderaweather.data.remote.model.DailyForecastApiModel
import com.example.banderaweather.data.remote.model.WeatherApiModel
import com.example.banderaweather.databinding.FragmentFirstBinding
import com.example.banderaweather.presentation.first.WeatherViewModel
import com.example.newnews.presentation.models.WeatherItem
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {
    private lateinit var binding: FragmentFirstBinding
    private var adapter = GroupieAdapter()
    private lateinit var itemList: List<DailyForecastApiModel>

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

//        val cities = enumValues<CityEnum>().map { it.name }
        val cities = arrayOf("Kharkiv", "Plus", "dvod")
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

        viewModel.getFiveDailyWeather(CityEnum.KYIV.cityKey)

        viewModel.weatherLiveData.observe(viewLifecycleOwner, Observer { it ->
            val itemList = it.DailyForecasts.map{ WeatherItem(it) }
            adapter.updateAsync(itemList)
        })

        val todayWeather = itemList[0]
        binding.todayDegree.text =

//
//        viewModel.getFiveDailyWeather(CityEnum.KYIV.cityKey)
    }

    private fun setupRecyclerView(){
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.setOnItemClickListener { item, view ->
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

}