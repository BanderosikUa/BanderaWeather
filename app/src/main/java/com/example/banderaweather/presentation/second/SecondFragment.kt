package com.example.banderaweather.presentation.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.banderaweather.R
import com.example.banderaweather.data.remote.model.IconApiEnum
import com.example.banderaweather.databinding.FragmentSecondBinding
import com.example.banderaweather.databinding.ItemShortWeatherBinding
import java.text.SimpleDateFormat
import java.util.*

class SecondFragment : Fragment() {
    lateinit var binding: FragmentSecondBinding
    lateinit var navController: NavController
    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        binding.dayName.text = getShortDayOfWeek(args.dayName)
        binding.dayDegree.text = args.dayDegree

        setDayImage()

        binding.rainProbability.text = args.rainProbability.toString() + "%"
        binding.windSpeed.text = args.windSpeed
        binding.longPhrase.text = args.longPhrase

        binding.humidity.text = args.humidity.toString()
        binding.uvindex.text = args.vuIndex.toString() + " of 10"
        binding.sunrise.text = getTimeFromDateTime(args.sunrise)
        binding.sunset.text = getTimeFromDateTime(args.sunset)

//        Glide.with(this)
//            .load(photo)
//            .into(binding.photo)
//
//        binding.backButton.setOnClickListener{
//            navController.navigate(R.id.action_secondFragment3_to_firstFragment2)
//        }
    }

    private fun getShortDayOfWeek(dateString: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(dateString) ?: return ""
        val dayOfWeekFormat = SimpleDateFormat("EEE|dd", Locale.getDefault())
        return dayOfWeekFormat.format(date)
    }

    private fun getTimeFromDateTime(dateString: String): String{
        val datetimeString = "2023-05-02 14:30:00"
        val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = parser.parse(datetimeString)
        val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return timeFormatter.format(date)
    }

    private fun setDayImage() {
        val dayImageView = binding.weatherImage
        val dayResourceId = dayImageView.context.resources.getIdentifier(
            IconApiEnum.getStringFromInt(args.dayIcon),
            "drawable",
            dayImageView.context.packageName
        )
        Glide.with(dayImageView.context)
            .load(dayResourceId)
            .override(35, 75)
            .into(dayImageView)
    }
}