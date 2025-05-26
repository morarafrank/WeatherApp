package com.morarafrank.weatherapp.utils


import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherSharedPrefs @Inject constructor(
    @ApplicationContext context: Context
) {
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(
        Constants.Preferences.SHARED_PREF_NAME,
        Context.MODE_PRIVATE
    )

        fun saveCity(cityName: String) {
        sharedPrefs.edit {
            putString("cityName", cityName)
        }
    }

    fun getCity(): String? {
        return sharedPrefs.getString("cityName", null)
    }

    fun clearCity() {
        sharedPrefs.edit {
            remove("cityName")
        }
    }

    fun clearWeatherSharedPrefs() {
        sharedPrefs.edit {
            clear()
        }
    }
}