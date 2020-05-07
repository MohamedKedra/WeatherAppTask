package com.mohamed.weatherapptask.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.weatherapptask.R
import com.mohamed.weatherapptask.models.Weather

class WeatherAdapter(
    private val context : Context,
    val items : List<Weather?>?
) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(LayoutInflater.from(context).inflate(R.layout.item_photo_weather,parent,false))
    }

    override fun getItemCount(): Int = 8

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(position : Int){

        }

    }
}