package com.morarafrank.weatherapp.ui.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.morarafrank.weatherapp.domain.model.ForecastItem
import com.morarafrank.weatherapp.utils.UiUtils

@Composable
fun ForecastCard(
    forecastItem: ForecastItem
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(2.dp)
            .size(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                forecastItem.weather.firstOrNull()?.main.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(4.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
            ) {

                WeatherIcon(
                    size = 50.dp,
                    iconCode = forecastItem.weather.firstOrNull()?.icon.toString()

                )

                Text(
                    "${forecastItem.main.temp.toInt()}°C" ,
                    style = MaterialTheme.typography.displayMedium
                )
            }
            Text(
               UiUtils.toCapitation( forecastItem.weather.firstOrNull()?.description.toString()),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = UiUtils.formatDate(forecastItem.dt),
                style = MaterialTheme.typography.labelSmall,
            )

            Text(
                text = UiUtils.formatTime(forecastItem.dt),
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}