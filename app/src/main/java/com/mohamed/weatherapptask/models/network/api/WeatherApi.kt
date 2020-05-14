package com.mohamed.weatherapptask.models.network.api

import com.mohamed.weatherapptask.models.network.response.WeatherApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getLocationExtraData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String) : Call<WeatherApiResponse>
}