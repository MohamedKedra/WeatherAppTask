package com.mohamed.weatherapptask.ui.home.view

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mohamed.weatherapptask.R
import com.mohamed.weatherapptask.app.base.BaseFragment
import com.mohamed.weatherapptask.models.db.models.WeatherPhoto
import com.mohamed.weatherapptask.ui.home.viewModel.HomeViewModel
import com.mohamed.weatherapptask.utils.ImageConverter
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream

class HomeFragment : BaseFragment(),OnListItemClickListener<WeatherPhoto> {

    override val layoutId: Int = R.layout.main_fragment

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showLayoutLoading()

        observeWeatherPhotos()

        fab.setOnClickListener {
            navigationController.navigate(R.id.action_homeFragment_to_AddNewFragment)
        }

        sr_refresh.setOnRefreshListener {
            observeWeatherPhotos()
        }
    }

    private fun observeWeatherPhotos(){
        sr_refresh.isRefreshing = true
        homeViewModel.allWeatherPhotos.observe(activity!!, Observer { weatherPhotos ->
            sr_refresh.isRefreshing = false
            hideLayoutErrorAndLoading()
            val  adapter = WeatherAdapter(
                context!!,
                this
            )
            rv_weather_photos.adapter = adapter


            rv_weather_photos.layoutManager = GridLayoutManager(context!!,2)
            if (weatherPhotos != null && weatherPhotos.isNotEmpty()) {

                adapter.setItems(weatherPhotos)
            } else {
                showLayoutError("No Data Found")
            }

        })
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        homeViewModel.deleteAllData()
        return super.onOptionsItemSelected(item)
    }
}
