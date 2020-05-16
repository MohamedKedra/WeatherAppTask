package com.mohamed.weatherapptask.utils

import android.content.Context
import android.graphics.*
import android.util.Base64
import android.view.WindowManager
import com.mohamed.weatherapptask.R
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt


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
        context: Context,
        bitmap: Bitmap,
        place: String,
        temp: String,
        condition: String
    ): Bitmap? {

        val displayMetrics = context.resources.displayMetrics
        val b = Bitmap.createScaledBitmap(bitmap,displayMetrics.widthPixels/2,displayMetrics.widthPixels/2,true)
        val canvas = Canvas(b)

        val paint = Paint()
        paint.color = Color.WHITE
        paint.textSize = 25f
        paint.isFakeBoldText = true
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)

        val transPaint = Paint()
        transPaint.color = Color.BLACK
        transPaint.alpha = 0x55

        canvas.drawBitmap(b, 0f, 0f, paint)
        canvas.drawRect(0F,0F,b.width.toFloat(),b.width.toFloat(), transPaint)
        canvas.drawText(place, (b.width / 2 - 50).toFloat(), (b.height - 50).toFloat(), paint)
        canvas.drawText(temp, (b.width / 2 - 50).toFloat(), (b.height - 100).toFloat(), paint)
        canvas.drawText(condition, (b.width / 2 - 50).toFloat(), (b.height - 150).toFloat(), paint)
        return b
    }

    private fun decodeSampledBitmapFromResource(
        context: Context
    ): Bitmap {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeResource(context.resources, R.drawable.ic_image,this)
            if(outWidth > display.width){
                val r = (outWidth.toFloat() / display.width.toFloat()).roundToInt()
                inSampleSize = r
            }
            inJustDecodeBounds = false
            BitmapFactory.decodeResource(context.resources,R.drawable.ic_image,this)
        }
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }

}