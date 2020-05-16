package com.mohamed.weatherapptask.ui.addNewPhotoWeather.view

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.mohamed.weatherapptask.R
import com.mohamed.weatherapptask.app.base.BaseFragment
import com.mohamed.weatherapptask.app.base.StateData
import com.mohamed.weatherapptask.models.db.models.WeatherPhoto
import com.mohamed.weatherapptask.ui.addNewPhotoWeather.viewModel.AddWeatherViewModel
import com.mohamed.weatherapptask.utils.ImageConverter
import com.mohamed.weatherapptask.utils.LocationProvider
import kotlinx.android.synthetic.main.add_weather_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddWeatherFragment : BaseFragment() {

    private val locationPermission: Int = 100
    private val requestCamera: Int = 101
    private val requestGallery: Int = 102
    private var mCurrentPhotoPath: Bitmap? = null

    companion object {
        private var isDataFetchedFromApi: Boolean = false
        private var isImageSelected: Boolean = false
    }

    private val viewModel: AddWeatherViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.add_weather_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //init weather layout
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        showLayoutError("Click to get Current Weather Data")

        fab_add_image.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED &&
                ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_DENIED
            ) {
                val permissions =
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
                requestPermissions(permissions, requestCamera)
                requestPermissions(permissions, requestGallery)
            } else {
                selectImage()
            }
        }

        btn_load.setOnClickListener {
            displayWeatherDataFromCurrentLocation()
        }

        btn_add.setOnClickListener {
            addNewWeatherPhotoIntoDb(it)
        }
    }

    private fun addNewWeatherPhotoIntoDb(it: View) {

        val place = tv_place.text.toString()
        val temp = tv_temp.text.toString()
        val condition = tv_status.text.toString()

        val bitmap = ImageConverter.drawTextOnImage(
            context!!, mCurrentPhotoPath!!, "Address is ".plus(place),
            "Temperature is ".plus(temp).plus("°C"),
            "Weather Condition is ".plus(condition)
        )
        loading_layout.visibility = View.VISIBLE
        if (isDataFetchedFromApi && isImageSelected) {
            val weatherPhoto = WeatherPhoto(
                photo = ImageConverter.convertBitmapToString(bitmap!!),
                place = place,
                temperature = temp,
                condition = condition
            )
            viewModel.insetNewWeatherIntoDb(weatherPhoto)
            loading_layout.visibility = View.GONE
            showMessage(it, "Weather Image Added Successfully")
            navigationController.popBackStack()
        } else if (!isImageSelected) {
            loading_layout.visibility = View.GONE
            showMessage(it, "pls select an Image")
        } else {
            loading_layout.visibility = View.GONE
            showMessage(it, "pls update your weather")
        }
    }

    private fun displayWeatherDataFromCurrentLocation() {

        LocationProvider.getInstance(context!!).getCurrentLocation().observe(
            this, Observer<Location> { location ->
                if (location != null) {
                    viewModel.getWeatherFromLocation(
                        location.latitude.toString(),
                        location.longitude.toString()
                    )
                        .observe(activity!!, Observer {
                            when (it.getStatus()) {

                                StateData.DataStatus.LOADING -> {
                                    fl_container.visibility = View.GONE
                                    showLayoutLoading()
                                }

                                StateData.DataStatus.SUCCESS -> {
                                    fl_container.visibility = View.VISIBLE
                                    hideLayoutErrorAndLoading()
                                    it?.getData()?.let { res ->
                                        isDataFetchedFromApi = true
                                        tv_place.text = res.name
                                        tv_temp.text = res.main.temp.toString()
                                        tv_status.text = res.weather[0].main
                                    } ?: run {
                                        showLayoutError("No Data found in list")
                                    }
                                }

                                StateData.DataStatus.ERROR -> {
                                    fl_container.visibility = View.GONE
                                    showLayoutError(it.getError()?.message.toString())
                                }

                                StateData.DataStatus.NOT_COMPLETED -> {
                                    fl_container.visibility = View.GONE
                                    showLayoutError("Something wrong happened")
                                }

                                StateData.DataStatus.NO_INTERNET -> {
                                    fl_container.visibility = View.GONE
                                    showLayoutError("No Internet Connection")
                                }
                            }
                        })
                } else {
                    requestPermissions()
                }
            }
        )
    }

    private fun selectImage() {

        val items: Array<CharSequence> = arrayOf("Camera", "Gallery", "Cancel")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Add Image")
        builder.setItems(items) { dialogInterface, i ->

            when (i) {
                0 -> {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(intent, requestCamera)
                }
                1 -> {

                    val intent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    intent.type = "image/*"
                    intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    startActivityForResult(
                        intent,
                        requestGallery
                    )
                }
                2 -> {
                    dialogInterface.dismiss()
                }
            }
        }

        builder.show()
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            activity!!,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            locationPermission
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {

            locationPermission -> {
                displayWeatherDataFromCurrentLocation()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                requestCamera -> {
                    mCurrentPhotoPath = data?.extras?.get("data") as Bitmap
                }
                requestGallery -> {
                    mCurrentPhotoPath =
                        MediaStore.Images.Media.getBitmap(activity?.contentResolver, data?.data)
                }
            }
            iv_photo.setImageBitmap(mCurrentPhotoPath)
            isImageSelected = true
        }
    }
}
