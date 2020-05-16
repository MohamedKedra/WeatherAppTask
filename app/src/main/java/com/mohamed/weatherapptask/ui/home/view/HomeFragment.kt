package com.mohamed.weatherapptask.ui.home.view

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.lifecycle.Observer
import com.mohamed.weatherapptask.R
import com.mohamed.weatherapptask.app.base.BaseFragment
import com.mohamed.weatherapptask.models.db.models.WeatherPhoto
import com.mohamed.weatherapptask.ui.home.viewModel.HomeViewModel
import com.mohamed.weatherapptask.utils.ImageConverter
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class HomeFragment : BaseFragment(),OnListItemClickListener<WeatherPhoto> {

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
                        weatherPhotos,
                        this
                    )
            } else {
                showLayoutError("No Data Found")
            }

        })

        fab.setOnClickListener {
            navigationController.navigate(R.id.action_homeFragment_to_AddNewFragment)
        }
    }

    override fun onItemClick(view: View, model: WeatherPhoto) {

        try{
            val intent = Intent()
            val baos = ByteArrayOutputStream()
            val bitmap = ImageConverter.convertStringToBitmap(model.photo)
            bitmap.compress(Bitmap.CompressFormat.PNG,100,baos)
            val path = MediaStore.Images.Media.insertImage(context?.contentResolver,bitmap,"img",null)
            intent.action = Intent.ACTION_SEND
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path))
            startActivity(Intent.createChooser(intent, "Share"))
        }catch (e : Exception){
            e.printStackTrace();
        }

    }
}
