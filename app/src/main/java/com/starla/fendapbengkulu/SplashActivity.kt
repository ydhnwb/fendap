package com.starla.fendapbengkulu

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    private val mCallback = Runnable {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        mHandler.postDelayed(mCallback, 2000)
    }

    override fun onResume() {
        super.onResume()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}
