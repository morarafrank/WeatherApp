package com.morarafrank.weatherapp.ui.screens.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WeatherIcon(
    iconCode: String,
    modifier: Modifier = Modifier,
    size: Dp
) {
    val imageUrl = "https://openweathermap.org/img/wn/${iconCode}@2x.png"

    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = "Weather Icon",
        modifier = modifier.size(size),
        contentScale = ContentScale.Fit
    )
}
