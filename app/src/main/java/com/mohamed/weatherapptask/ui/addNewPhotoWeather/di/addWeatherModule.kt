package com.mohamed.weatherapptask.ui.addNewPhotoWeather.di

import com.mohamed.weatherapptask.ui.addNewPhotoWeather.data.AddWeatherRepository
import com.mohamed.weatherapptask.ui.addNewPhotoWeather.viewModel.AddWeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var addWeatherModule = module {

    single {
        AddWeatherRepository(get())
    }

    viewModel {
        AddWeatherViewModel(get(),get())
    }
}