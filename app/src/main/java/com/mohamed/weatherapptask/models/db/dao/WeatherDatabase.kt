package com.mohamed.weatherapptask.models.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohamed.weatherapptask.models.db.models.WeatherPhoto

@Database(entities = [WeatherPhoto::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}