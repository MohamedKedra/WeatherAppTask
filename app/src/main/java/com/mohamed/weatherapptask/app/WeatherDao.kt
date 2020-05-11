package com.mohamed.weatherapptask.app

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohamed.weatherapptask.models.Weather

@Dao
interface WeatherDao {
    @Query("SELECT * from weather_table")
    fun getCachedWeathers(): List<Weather>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNewWeather(weather: Weather)
}