package com.mohamed.articaledemoapp.ui.main.di

import android.app.Application
import com.mohamed.weatherapptask.ui.addNewPhotoWeather.di.addWeatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationDemo : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ApplicationDemo)
            modules(
                listOf(
                    appModule,
                    addWeatherModule
                )
            )
        }

    }

}