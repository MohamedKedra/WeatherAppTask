package com.mohamed.weatherapptask.app.base

import com.mohamed.weatherapptask.app.WeatherDao
import kotlinx.coroutines.CoroutineScope

abstract class BaseRepository(protected val weatherDao: WeatherDao)