package com.mohamed.weatherapptask.models.network.response


import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("grnd_level")
    val grndLevel: String,
    @SerializedName("humidity")
    val humidity: String,
    @SerializedName("pressure")
    val pressure: String,
    @SerializedName("sea_level")
    val seaLevel: String,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)