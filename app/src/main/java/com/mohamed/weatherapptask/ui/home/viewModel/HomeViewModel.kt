package com.mohamed.weatherapptask.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mohamed.weatherapptask.models.db.models.WeatherPhoto
import com.mohamed.weatherapptask.ui.home.data.HomeWeatherRepository

class HomeViewModel(
    repository: HomeWeatherRepository
) : ViewModel() {

    var allWeatherPhotos: LiveData<List<WeatherPhoto>> = repository.allWeatherPhotos
}
