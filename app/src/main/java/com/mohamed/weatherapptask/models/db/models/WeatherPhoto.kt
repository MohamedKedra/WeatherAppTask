package com.mohamed.weatherapptask.models.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
class WeatherPhoto(
    var photo: String,
    var place: String,
    var temperature: String,
    var condition: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}