package com.mohamed.weatherapptask.ui.addNewPhotoWeather.viewModel

import android.net.ConnectivityManager
import com.mohamed.articaledemoapp.ui.main.ui.bases.BaseViewModel
import com.mohamed.articaledemoapp.ui.main.ui.bases.StateLiveData
import com.mohamed.weatherapptask.models.network.response.WeatherApiResponse
import com.mohamed.weatherapptask.ui.addNewPhotoWeather.data.AddWeatherRepository

class AddWeatherViewModel(
    private val repository: AddWeatherRepository,
    connectivityManager: ConnectivityManager
) : BaseViewModel(connectivityManager){

    private val allWeatherLiveData : StateLiveData<WeatherApiResponse> = StateLiveData()

    fun addNewPhotoWeather(lat : String,lon : String) : StateLiveData<WeatherApiResponse>{

        if(!isNetworkAvailable){
            publishNoInternet(allWeatherLiveData)
            return allWeatherLiveData
        }

        performApiCall(allWeatherLiveData,repository.getWeatherExtraData(lat,lon))

        publishLoading(allWeatherLiveData)

        return allWeatherLiveData
    }
}
