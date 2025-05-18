package com.morarafrank.weatherapp.utils

import android.content.Context
import android.content.SharedPreferences

object WeatherSharedPrefs {

    private lateinit var sharedPrefs: SharedPreferences
    fun initSharedPrefs(context: Context) {
        sharedPrefs = context.getSharedPreferences(
            Constants.Preferences.SHARED_PREF_NAME, Context.MODE_PRIVATE
        )
    }


}