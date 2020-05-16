package com.mohamed.weatherapptask.app.base

import com.mohamed.weatherapptask.models.db.dao.WeatherDao

open class BaseRepository(protected val weatherDao: WeatherDao)