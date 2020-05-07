package com.mohamed.weatherapptask.ui.main

import android.os.Bundle
import com.mohamed.weatherapptask.R
import com.mohamed.weatherapptask.app.base.BaseFragment
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : BaseFragment() {

    override val layoutId: Int = R.layout.main_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_weather_photos.adapter = WeatherAdapter(context!!,null)

        fab.setOnClickListener {
            navigationController.navigate(R.id.action_homeFragment_to_AddNewFragment)
        }
    }

}
