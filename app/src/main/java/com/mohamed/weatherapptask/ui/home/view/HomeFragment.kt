package com.mohamed.weatherapptask.ui.home.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.mohamed.weatherapptask.R
import com.mohamed.weatherapptask.app.base.BaseFragment
import com.mohamed.weatherapptask.ui.home.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    override val layoutId: Int = R.layout.main_fragment

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showLayoutLoading()
        homeViewModel.allWeatherPhotos.observe(this, Observer { weatherPhotos ->
            hideLayoutErrorAndLoading()
            if (weatherPhotos != null && weatherPhotos.isNotEmpty()) {
                rv_weather_photos.adapter =
                    WeatherAdapter(
                        context!!,
                        weatherPhotos
                    )
            } else {
                showLayoutError("No Data Found")
            }

        })

        fab.setOnClickListener {
            navigationController.navigate(R.id.action_homeFragment_to_AddNewFragment)
        }
    }
}
