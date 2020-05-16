package com.mohamed.weatherapptask.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.media.ThumbnailUtils
import android.util.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


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

    fun drawTextOnImage(
        bitmap: Bitmap,
        text: String
    ): Bitmap? {

        val b = Bitmap.createBitmap(bitmap.width,bitmap.height,bitmap.config)
        val canvas = Canvas(b)

        val paint = Paint()
        paint.color = Color.BLACK
        paint.textSize = 30f
        paint.isFakeBoldText = true
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
        val transPaint = Paint()
        transPaint.color = Color.WHITE
        transPaint.alpha = 0x55
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        canvas.drawRect(0F,0F,canvas.width.toFloat(),canvas.height.toFloat(),transPaint)
        canvas.drawText(text, (bitmap.width / 2 - 50).toFloat(), (bitmap.height - 50).toFloat(), paint)
        return b
    }

}