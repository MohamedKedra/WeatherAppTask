package com.mohamed.weatherapptask.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class Weather(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "place") val placeName: String,
    @ColumnInfo(name = "temp") val temperature: String,
    @ColumnInfo(name = "condition") val weatherCondition: String
)