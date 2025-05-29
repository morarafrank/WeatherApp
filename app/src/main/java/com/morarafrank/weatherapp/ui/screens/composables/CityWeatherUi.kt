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
import com.morarafrank.weatherapp.utils.UiUtils

@Composable
fun CityWeatherUi(
    modifier: Modifier = Modifier,
    weatherData: WeatherResponse?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 8.dp, end = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(8.dp, Alignment.CenterVertically)
    ) {

        Text(
            text = "${weatherData?.name}, ${weatherData?.sys?.country}",
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
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "${weatherData
                        ?.main
                        ?.temp
                        ?.toInt()} °C",
                    style = MaterialTheme.typography.labelLarge
                )
//                Text(
//                    text = "• Feels Like${weatherData?.main?.feels_like?.toInt()}°C",
//                    style = MaterialTheme.typography.displaySmall
//                )
                Text(
                    text = "• Humidity: ${weatherData?.main?.humidity} %",
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = "• Wind: ${weatherData?.wind?.speed} m/s",
                    style = MaterialTheme.typography.displaySmall
                )

                Text(
                    text = UiUtils.toCapitation(
                        weatherData
                            ?.weather
                            ?.firstOrNull()
                            ?.description
                            .toString()
                    ),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }



        Text(
            text = "5-Day Forecast: ",
            style = MaterialTheme.typography.bodyLarge,
        )

        Text(
            text = "Last updated on: ${UiUtils.formatDate(weatherData?.dt)} at ${UiUtils.formatTime(weatherData?.dt)}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

    }
}