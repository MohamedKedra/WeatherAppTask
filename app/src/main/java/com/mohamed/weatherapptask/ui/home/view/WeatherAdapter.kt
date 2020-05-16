package com.mohamed.weatherapptask.ui.home.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.weatherapptask.R
import com.mohamed.weatherapptask.models.db.models.WeatherPhoto
import com.mohamed.weatherapptask.utils.ImageConverter
import kotlinx.android.synthetic.main.item_photo_weather.view.*

class WeatherAdapter(
    private val context: Context,
    val items: List<WeatherPhoto>

) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_photo_weather,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = items[position]
        if (item.photo.isNotEmpty()) holder.itemView.ivImage.setImageBitmap(
            ImageConverter.convertStringToBitmap(
                item.photo
            )
        )
        holder.itemView.tv_place.text = item.place
        holder.itemView.tv_temp.text = item.temperature
        holder.itemView.tv_status.text = item.condition
    }

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}