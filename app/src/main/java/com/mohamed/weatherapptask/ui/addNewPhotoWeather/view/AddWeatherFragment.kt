package com.mohamed.weatherapptask.ui.addNewPhotoWeather.view

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.mohamed.weatherapptask.R
import com.mohamed.weatherapptask.app.base.BaseFragment
import kotlinx.android.synthetic.main.add_weather_fragment.*

class AddWeatherFragment : BaseFragment() {

    private val requestCamera: Int = 1
    private val requestGallery: Int = 2
    private var mCurrentPhotoPath : String? = null

    override val layoutId: Int
        get() = R.layout.add_weather_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar_main.title = "Add New Weather"
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)

        fab_add_image.setOnClickListener {
            if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED &&
                ContextCompat.checkSelfPermission(context!!,Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA);
                requestPermissions(permissions, requestCamera)
                requestPermissions(permissions, requestGallery)
            } else {
                selectImage()
            }
        }
    }

    private fun selectImage() {

        val items: Array<CharSequence> = arrayOf("Camera", "Gallery", "Cancel")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Add Image")
        builder.setItems(items, DialogInterface.OnClickListener { dialogInterface, i ->

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
        })

        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {

            when (requestCode) {

                requestCamera -> {
                    val photo = data?.extras?.get("data") as Bitmap
                    iv_photo.setImageBitmap(photo)
                }
                requestGallery -> {
                    iv_photo.setImageURI(data?.data)
                }
            }
        }
    }
}
