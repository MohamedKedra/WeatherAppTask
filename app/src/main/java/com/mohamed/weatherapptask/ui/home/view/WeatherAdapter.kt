package com.mohamed.weatherapptask.ui.home.view

import android.content.Context
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.ContactsContract
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
    val items: List<WeatherPhoto>,
    private val onListItemClickListener: OnListItemClickListener<WeatherPhoto>
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
        if (item.photo.isNotEmpty()) {
            val bit : Bitmap = ImageConverter.convertStringToBitmap(item.photo)

            val displayMetrics = context.resources.displayMetrics
            holder.itemView.ivImage.setImageBitmap(Bitmap.createScaledBitmap(bit,displayMetrics.widthPixels/2,displayMetrics.widthPixels/2,true))
        }
    }

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onListItemClickListener.onItemClick(itemView,items[adapterPosition])
        }

    }
}