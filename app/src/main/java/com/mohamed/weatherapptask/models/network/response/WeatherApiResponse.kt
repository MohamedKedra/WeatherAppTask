package com.mohamed.weatherapptask.models.network.response


import com.google.gson.annotations.SerializedName

data class WeatherApiResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("dt")
    val dt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
)