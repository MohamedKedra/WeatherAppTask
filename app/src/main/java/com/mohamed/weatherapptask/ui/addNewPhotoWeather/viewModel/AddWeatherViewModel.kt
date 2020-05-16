package com.mohamed.weatherapptask.ui.addNewPhotoWeather.viewModel

import android.net.ConnectivityManager
import androidx.lifecycle.viewModelScope
import com.mohamed.weatherapptask.app.base.BaseViewModel
import com.mohamed.weatherapptask.app.base.StateLiveData
import com.mohamed.weatherapptask.models.db.models.WeatherPhoto
import com.mohamed.weatherapptask.models.network.response.WeatherApiResponse
import com.mohamed.weatherapptask.ui.addNewPhotoWeather.data.AddWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddWeatherViewModel(
    private val repository: AddWeatherRepository,
    connectivityManager: ConnectivityManager
) : BaseViewModel(connectivityManager) {

    private val allWeatherLiveData: StateLiveData<WeatherApiResponse> = StateLiveData()

    fun getWeatherFromLocation(lat: String, lon: String): StateLiveData<WeatherApiResponse> {

        if (!isNetworkAvailable) {
            publishNoInternet(allWeatherLiveData)
            return allWeatherLiveData
        }

        performApiCall(allWeatherLiveData, repository.getWeatherExtraData(lat, lon))

        publishLoading(allWeatherLiveData)

        return allWeatherLiveData
    }

    fun insetNewWeatherIntoDb(weatherPhoto: WeatherPhoto) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertWeather(weatherPhoto)
    }
}
