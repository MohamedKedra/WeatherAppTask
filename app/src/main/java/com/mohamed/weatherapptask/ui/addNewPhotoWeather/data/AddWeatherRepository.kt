package com.mohamed.weatherapptask.ui.addNewPhotoWeather.data

import androidx.annotation.WorkerThread
import com.mohamed.weatherapptask.app.base.BaseRepository
import com.mohamed.weatherapptask.models.db.dao.WeatherDao
import com.mohamed.weatherapptask.models.db.models.WeatherPhoto
import com.mohamed.weatherapptask.models.network.api.WeatherApi
import com.mohamed.weatherapptask.models.network.response.WeatherApiResponse
import com.mohamed.weatherapptask.utils.Constants
import retrofit2.Call

class AddWeatherRepository(private val weatherApi: WeatherApi, weatherDao: WeatherDao) :
    BaseRepository(
        weatherDao
    ) {

    fun getWeatherExtraData(lat: String, lon: String): Call<WeatherApiResponse> =
        weatherApi.getLocationExtraData(
            lat, lon,
            Constants.api.API_KEY
        )

    @WorkerThread
    fun insertWeather(weatherPhoto: WeatherPhoto) {
        weatherDao.insertNewWeather(weatherPhoto)
    }
}