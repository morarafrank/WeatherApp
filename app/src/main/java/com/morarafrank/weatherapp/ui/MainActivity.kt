package com.morarafrank.weatherapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.morarafrank.weatherapp.ui.screens.WeatherDetailsScreen
import com.morarafrank.weatherapp.ui.theme.WeatherAppTheme
import com.morarafrank.weatherapp.utils.WeatherSharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var sharedPrefs: WeatherSharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefs = WeatherSharedPrefs(this)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                WeatherDetailsScreen()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        sharedPrefs.clearWeatherSharedPrefs()

    }
}