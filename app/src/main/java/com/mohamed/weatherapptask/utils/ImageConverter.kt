package com.mohamed.weatherapptask.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

object ImageConverter {

    fun convertBitmapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun convertStringToBitmap(image : String) : Bitmap{
        val base64 = Base64.decode(image,Base64.DEFAULT)
        val bais = ByteArrayInputStream(base64)
        return BitmapFactory.decodeStream(bais)
    }
}