package com.mohamed.weatherapptask.app.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.mohamed.weatherapptask.models.db.dao.WeatherDatabase
import com.mohamed.weatherapptask.models.network.api.WeatherApi
import com.mohamed.weatherapptask.utils.Constants
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
        Room.databaseBuilder(
            get(),
            WeatherDatabase::class.java, Constants.db.DB_NAME
        )
            .build()
    }

    single {
        get<WeatherDatabase>().weatherDao()
    }

    single {
        get<Context>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}