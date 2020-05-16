package com.mohamed.weatherapptask.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.weatherapptask.models.db.models.WeatherPhoto
import com.mohamed.weatherapptask.ui.home.data.HomeWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeWeatherRepository
) : ViewModel() {

    var allWeatherPhotos: LiveData<List<WeatherPhoto>> = repository.allWeatherPhotos
    
    
    fun deleteAllData() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}
