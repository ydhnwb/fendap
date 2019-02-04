package com.starla.fendapbengkulu

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.starla.fendapbengkulu.utilities.Others
import kotlinx.android.synthetic.main.activity_photo_view.*

class PhotoViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)
        supportActionBar?.hide()
        try{
            Glide.with(this@PhotoViewActivity).load(getImageUrl()).apply(RequestOptions().placeholder(R.drawable.placeholder).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                    .into(photoview_photo)

        }catch(e: Exception){
            System.err.println("ydhnwb : ${e.message}")
            Toast.makeText(this@PhotoViewActivity, "Tidak dapat memuat gambar", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun getImageUrl() = intent.getStringExtra("TARGET")
}
