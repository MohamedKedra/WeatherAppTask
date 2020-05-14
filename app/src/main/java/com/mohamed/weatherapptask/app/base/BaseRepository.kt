package com.mohamed.weatherapptask.app.base

import com.mohamed.weatherapptask.app.WeatherDao
import com.mohamed.weatherapptask.models.network.api.WeatherApi
import kotlinx.coroutines.CoroutineScope

abstract class BaseRepository(protected val weatherApi: WeatherApi)