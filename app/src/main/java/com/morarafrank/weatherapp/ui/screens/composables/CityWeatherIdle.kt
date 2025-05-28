package com.morarafrank.weatherapp.ui.screens.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CityWeatherIdle(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement
            .spacedBy(12.dp, Alignment.CenterVertically)
    ) {

         Text(
             text = "Welcome to the Weather App",
             style = MaterialTheme.typography.bodyLarge
         )
         Text(
             text = "Search for city to get it's weather",
             style = MaterialTheme.typography.bodyMedium
         )

    }
}