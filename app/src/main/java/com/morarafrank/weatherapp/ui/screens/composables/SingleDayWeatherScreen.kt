package com.morarafrank.weatherapp.ui.screens.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.morarafrank.merchants.R
import com.morarafrank.weatherapp.domain.model.WeatherResponse
import com.morarafrank.weatherapp.ui.WeatherAppViewModel
import com.morarafrank.weatherapp.utils.UiUtils

//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleDayWeatherScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    viewModel: WeatherAppViewModel = hiltViewModel()
) {

    val weatherData = viewModel.weatherData.value

    Scaffold(
        topBar = {
           TopAppBar(
               title = {},
               navigationIcon = {
                   IconButton(
                       onClick = {
                           navigateBack()
                       }
                   ) {
                       Icon(
                           painter = painterResource(R.drawable.ic_arrow_back),
                           contentDescription = "Search"
                       )
                   }
               }
           )
        },
        content = {
            SingleDayWeatherContent(
                modifier = modifier
                    .padding(it),
                weatherData = weatherData
            )
        },
    )
}


@Composable
fun SingleDayWeatherContent(
    modifier: Modifier = Modifier,
    weatherData: WeatherResponse?
) {
    Column(
    modifier = modifier
        .fillMaxWidth()
        .padding(start = 8.dp, end = 8.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
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
        style = MaterialTheme.typography.bodyMedium
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
        text = "Last updated on: ${UiUtils.formatDate(weatherData?.dt)} at ${UiUtils.formatTime(weatherData?.dt)}",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
    }
}