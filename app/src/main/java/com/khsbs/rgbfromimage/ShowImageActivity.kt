package com.khsbs.rgbfromimage

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_show_image.*

class ShowImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)

        with(iv_clickableImg) {
            layoutParams.apply { height = width }
            requestLayout()
        }

        val imgFromPreviousActivity: Uri = intent.getParcelableExtra("selectedImg")

        Glide.with(this)
            .load(imgFromPreviousActivity)
            .error(R.mipmap.ic_launcher)
            .into(iv_clickableImg)

        iv_clickableImg.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) getPos(motionEvent)
            true
        }
    }

    @SuppressLint("SetTextI18n")
    fun getPos(event: MotionEvent) {
        tv_selectedPos.text = resources.getString(R.string.selected_position) + event.x + " " + event.y

        val bmp = ((iv_clickableImg.drawable) as BitmapDrawable).bitmap

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val realX = event.x * size.x / size.y
        val realY = event.y * size.x / size.y
        val pixel = bmp.getPixel(realX.toInt(), realY.toInt())

        tv_rgbValue.text = "R : " + Color.red(pixel) +
                " G : " +  Color.green(pixel) +
                " B : " + Color.blue(pixel)
        tv_rgbValue.setTextColor(Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel)))
    }

}
