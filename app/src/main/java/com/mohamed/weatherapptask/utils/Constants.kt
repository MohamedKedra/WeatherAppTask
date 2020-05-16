package com.mohamed.weatherapptask.utils

class Constants {

    object api {
        private val BASE_URL = "https://api.openweathermap.org/"
        private val API_VERSION = "/data/2.5/"
        val URL = BASE_URL + API_VERSION
        val API_KEY = "39b73c6316122fae0ad366599960cbce"
    }

    object db {
        val DB_NAME = "Weather_Database"
    }
}