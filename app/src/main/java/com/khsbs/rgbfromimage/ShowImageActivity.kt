package com.khsbs.rgbfromimage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_show_image.*

class ShowImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)

        with(iv_clickableImg) {
            layoutParams.apply { height = width }
            requestLayout()
            setImageURI(intent?.getParcelableExtra("selectedImg"))
        }
    }
}
