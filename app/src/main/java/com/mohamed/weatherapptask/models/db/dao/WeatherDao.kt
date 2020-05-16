package com.mohamed.weatherapptask.models.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohamed.weatherapptask.models.db.models.WeatherPhoto

@Dao
interface WeatherDao {
    @Query("SELECT * from weather_table")
    fun getCachedWeathers(): LiveData<List<WeatherPhoto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNewWeather(weather: WeatherPhoto)

    @Query("DELETE FROM weather_table")
    fun deleteAll()
}