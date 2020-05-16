package com.mohamed.weatherapptask.ui.home.di

import com.mohamed.weatherapptask.ui.home.data.HomeWeatherRepository
import com.mohamed.weatherapptask.ui.home.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single {
        HomeWeatherRepository(get())
    }

    viewModel {
        HomeViewModel(get())
    }

}