package com.morarafrank.weatherapp.ui.screens

import android.os.Handler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.morarafrank.weatherapp.R
import com.morarafrank.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.delay

//@Preview(showBackground = true)
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateToWeatherDetails: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(3000L)
        navigateToWeatherDetails()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "WeatherApp",
            style = MaterialTheme.typography.headlineLarge,
            )

    }
}

@Preview
@Composable
private fun PrevSplashScreen() {
    WeatherAppTheme {
        SplashScreen(
            navigateToWeatherDetails = {}
        )
    }
}