package com.morarafrank.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.morarafrank.weatherapp.R
import com.morarafrank.weatherapp.ui.WeatherAppViewModel
import com.morarafrank.weatherapp.ui.screens.composables.ForecastCard
import com.morarafrank.weatherapp.ui.theme.WeatherAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.morarafrank.weatherapp.ui.screens.composables.CityWeatherErrorUi
import com.morarafrank.weatherapp.ui.screens.composables.CityWeatherIdle
import com.morarafrank.weatherapp.ui.screens.composables.CityWeatherLoadingUi
import com.morarafrank.weatherapp.ui.screens.composables.CityWeatherUi
import com.morarafrank.weatherapp.ui.screens.composables.EmptyForecastUi
import com.morarafrank.weatherapp.ui.screens.composables.ForecastUi
import com.morarafrank.weatherapp.ui.state.ForecastUiState
import com.morarafrank.weatherapp.ui.state.WeatherUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailsScreen(
    navigateToSearch: () -> Unit,
    viewModel: WeatherAppViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    city: String? = null,
) {

    val weatherUiState by viewModel.weatherUiState.collectAsStateWithLifecycle()
    val forecastUiState by viewModel.forecastUiState.collectAsStateWithLifecycle()


    if (city != null) {
        LaunchedEffect(city) {
            viewModel.onCitySearched(city)
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "WeatherApp",
                    )
                },
                actions = {
                    IconButton(onClick = navigateToSearch) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(it)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
            ) {

                // Weather details
                Column(
                    modifier = modifier.fillMaxWidth()
                        .weight(2f, true),
                ) {

                    when(weatherUiState){

                        is WeatherUiState.Idle -> {
                            CityWeatherIdle()
                        }
                        is WeatherUiState.Loading -> {
                            CityWeatherLoadingUi()
                        }
                        is WeatherUiState.Success -> {
                            CityWeatherUi()
                        }
                        is WeatherUiState.Error -> {
                            CityWeatherErrorUi()
                        }
                    }

                }


                // Forecast
                Column(
                    modifier = modifier.fillMaxWidth()
                        .weight(1f, true),
                ) {

                    when(forecastUiState){
                        is ForecastUiState.Idle -> {
                            EmptyForecastUi(
                                "No forecast data available.",
                            )
                        }
                        is ForecastUiState.Loading -> {
                            CityWeatherLoadingUi()
                        }
                        is ForecastUiState.Success -> {
                            ForecastUi()
                        }
                        is ForecastUiState.Error -> {
                            CityWeatherErrorUi(
                                errorMessage = "Could not load forecast data.",
                                onRetry = {}
                            )
                        }
                    }
                }


            }
        },
        bottomBar = {}
    )
}


//@Preview
//@Composable
//private fun PrevHome() {
//    WeatherAppTheme {
//        WeatherDetailsScreen(
//            navigateToSearch = {}
//        )
//    }
//}