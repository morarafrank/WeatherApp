package com.morarafrank.weatherapp.ui.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morarafrank.weatherapp.R
import com.morarafrank.weatherapp.domain.model.WeatherResponse

@Composable
fun CityWeatherUi(
    modifier: Modifier = Modifier,
    weatherData: WeatherResponse?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(16.dp, Alignment.CenterVertically)
    ) {

        Text(
            text = weatherData
                ?.name
                .toString(),
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = weatherData
                ?.weather
                ?.firstOrNull()
                ?.main
                .toString(),
            style = MaterialTheme.typography.labelMedium.copy(
                fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
                fontSize = 20.sp
            )
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement
                .spacedBy(4.dp, Alignment.CenterHorizontally)
        ) {

            WeatherIcon(
                iconCode = weatherData
                    ?.weather
                    ?.firstOrNull()
                    ?.icon
                    .toString(),
                size = 120.dp
            )
            Text(
                text = "${weatherData
                    ?.main
                    ?.temp
                    ?.toInt()}°C",
                style = MaterialTheme.typography.labelMedium
            )
        }

        Text(
            text = weatherData
                ?.weather
                ?.firstOrNull()
                ?.description
                .toString(),
            style = MaterialTheme.typography.labelMedium
        )

        Text(

            text = "• Feels Like" +
                    " ${weatherData
                ?.main?.feels_like
                ?.toInt()}°C" +
                    " • Humidity: ${weatherData
                    ?.main
                    ?.humidity}%"
                    + " • Wind: ${weatherData
                        ?.wind
                        ?.speed} m/s",
            style = MaterialTheme.typography.labelMedium.copy(
                fontFamily = FontFamily(Font(R.font.dm_sans_regular)),
                fontSize = 16.sp
            )
        )

    }
}