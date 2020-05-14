package com.mohamed.weatherapptask.ui.addNewPhotoWeather.data

import com.mohamed.weatherapptask.app.Constants
import com.mohamed.weatherapptask.app.base.BaseRepository
import com.mohamed.weatherapptask.models.network.api.WeatherApi
import com.mohamed.weatherapptask.models.network.response.WeatherApiResponse
import retrofit2.Call

class AddWeatherRepository(weatherApi: WeatherApi) :
    BaseRepository(weatherApi) {

    fun getWeatherExtraData(lat : String, lon : String) : Call<WeatherApiResponse> =
        weatherApi.getLocationExtraData(lat,lon,Constants.api.API_KEY)
}