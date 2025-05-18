package com.morarafrank.weatherapp

import android.app.Application
import com.morarafrank.weatherapp.utils.WeatherSharedPrefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp: Application(){

    override fun onCreate() {
        super.onCreate()

        WeatherSharedPrefs.initSharedPrefs(this)
    }
    init {

    }
}