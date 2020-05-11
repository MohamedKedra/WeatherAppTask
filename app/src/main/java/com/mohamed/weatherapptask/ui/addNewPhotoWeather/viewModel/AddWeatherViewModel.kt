package com.mohamed.weatherapptask.ui.addNewPhotoWeather.viewModel

import androidx.lifecycle.ViewModel
import com.mohamed.weatherapptask.models.Weather
import com.mohamed.weatherapptask.ui.addNewPhotoWeather.data.AddWeatherRepository

class AddWeatherViewModel(private val repository: AddWeatherRepository) : ViewModel() {

    fun addNewWeather(weather: Weather){
        repository.addNewWeather(weather)
    }
}
