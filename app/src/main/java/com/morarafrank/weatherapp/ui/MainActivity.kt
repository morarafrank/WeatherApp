package com.morarafrank.weatherapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.morarafrank.weatherapp.ui.navigation.WeatherNavGraph
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
            val navController = rememberNavController()
            WeatherAppTheme {
                    WeatherNavGraph(
                        navController = navController
                    )
            }
        }
    }

    override fun onStop() {
        super.onStop()
        sharedPrefs.clearWeatherSharedPrefs()

    }
}