package com.khsbs.rgbfromimage

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.selector
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

var cameraFilePath: String? = null

const val PERMISSION_REQUEST = 1
const val GALLERY_REQUEST_CODE = 2
const val CAMERA_REQUEST_CODE = 3

class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission()

        btn_selectImg.setOnClickListener {
            selector(items = listOf("카메라", "갤러리")) { _, selection ->
                when (selection) {
                    0 -> pickFromCamera()
                    1 -> pickFromGallery()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> startActivity<ShowImageActivity>("selectedImg" to data?.data)
                CAMERA_REQUEST_CODE -> startActivity<ShowImageActivity>("selectedImg" to Uri.parse(cameraFilePath))
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
    fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                ), PERMISSION_REQUEST
            )
        }
    }

    fun pickFromGallery() {
        var mimeTypes = arrayOf("image/jpeg", "image/png")
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        }.let {
            startActivityForResult(it, GALLERY_REQUEST_CODE)
        }
    }

    fun pickFromCamera() {
        try {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(
                    this@MainActivity, BuildConfig.APPLICATION_ID + ".provider", createImageFile()
                ))
            }.let {
                startActivityForResult(it, CAMERA_REQUEST_CODE)
            }
        } catch (e: IOException) {
            toast(e.toString())
        }
    }

    @Throws(IOException::class)
    fun createImageFile() : File {
        val timeStamp = SimpleDateFormat("yyyyMMDD_HHmmss").format(Date())
        val imageFileName = "JPEG" + timeStamp + "_"

        val storageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera")
        val image = File.createTempFile(
            imageFileName,
            ".jpg",
            storageDir
        )
        cameraFilePath = "file://" + image.absolutePath
        return image
    }
}

