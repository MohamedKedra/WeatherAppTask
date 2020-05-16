package com.mohamed.weatherapptask.ui.home.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.mohamed.weatherapptask.app.base.BaseRepository
import com.mohamed.weatherapptask.models.db.dao.WeatherDao
import com.mohamed.weatherapptask.models.db.models.WeatherPhoto

class HomeWeatherRepository(weatherDao: WeatherDao) : BaseRepository(weatherDao) {

    val allWeatherPhotos: LiveData<List<WeatherPhoto>> = weatherDao.getCachedWeathers()

    @WorkerThread
    fun deleteAll(){
        weatherDao.deleteAll()
    }
}