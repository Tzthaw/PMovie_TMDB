package com.mmgoogleexpert.ptut.pmovie_tmdb

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.mmgoogleexpert.ptut.pmovie_tmdb.activities.HomeActivity


class SplashScreen : AppCompatActivity() {
    private val mHandler = Handler()
    private val mLauncher = Launcher()

    override fun onStart() {
        super.onStart()
        mHandler.postDelayed(mLauncher, SPLASH_DELAY.toLong())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStop() {
        mHandler.removeCallbacks(mLauncher)
        super.onStop()
    }

    private fun launch() {
        if (!isFinishing) {
            startActivity(Intent(this@SplashScreen, HomeActivity::class.java))
            finish()
        }
    }

    private inner class Launcher : Runnable {
        override fun run() {
            launch()
        }
    }

    companion object {
         const val SPLASH_DELAY = 3000
    }
}