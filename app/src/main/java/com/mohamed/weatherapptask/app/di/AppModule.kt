package com.mohamed.articaledemoapp.ui.main.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.mohamed.weatherapptask.app.Constants
import com.mohamed.weatherapptask.app.WeatherDatabase
import com.mohamed.weatherapptask.models.network.api.WeatherApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder().baseUrl(Constants.api.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(WeatherApi::class.java)
    }

    single {
        Room.databaseBuilder(get(),WeatherDatabase::class.java,"weather_database")
    }

    single {
        get<WeatherDatabase>().WeatherDao()
    }

    single {
        get<Context>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}