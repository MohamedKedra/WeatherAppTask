package com.mohamed.weatherapptask.ui.addNewPhotoWeather.data

import com.mohamed.weatherapptask.app.WeatherDao
import com.mohamed.weatherapptask.app.base.BaseRepository
import com.mohamed.weatherapptask.models.Weather
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AddWeatherRepository(weatherDao: WeatherDao) : BaseRepository(weatherDao) ,CoroutineScope{

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun addNewWeather(weather: Weather) {
        launch {
            withContext(Dispatchers.IO){
                weatherDao.insertNewWeather(weather)
            }
        }
    }
}