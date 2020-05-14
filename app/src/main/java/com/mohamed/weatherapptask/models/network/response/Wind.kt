package com.mohamed.weatherapptask.models.network.response


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    val deg: String,
    @SerializedName("speed")
    val speed: Double
)