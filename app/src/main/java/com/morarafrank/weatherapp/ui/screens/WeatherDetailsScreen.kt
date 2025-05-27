package com.morarafrank.weatherapp.ui.screens

import android.os.Build.VERSION_CODES.O
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.morarafrank.weatherapp.ui.WeatherAppViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.morarafrank.weatherapp.ui.screens.composables.CitySearchUi
import com.morarafrank.weatherapp.ui.screens.composables.CityWeatherErrorUi
import com.morarafrank.weatherapp.ui.screens.composables.CityWeatherIdle
import com.morarafrank.weatherapp.ui.screens.composables.CityWeatherLoadingUi
import com.morarafrank.weatherapp.ui.screens.composables.CityWeatherUi
import com.morarafrank.weatherapp.ui.screens.composables.EmptyForecastUi
import com.morarafrank.weatherapp.ui.screens.composables.ForecastUi
import com.morarafrank.weatherapp.ui.state.ForecastUiState
import com.morarafrank.weatherapp.ui.state.WeatherUiState
import com.morarafrank.weatherapp.utils.UiUtils
@RequiresApi(O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherAppViewModel = hiltViewModel(),
) {

    val weatherUiState by viewModel.weatherUiState.collectAsStateWithLifecycle()
    val forecastUiState by viewModel.forecastUiState.collectAsStateWithLifecycle()

    val weatherData = viewModel.weatherData.value
    val forecastData by viewModel.fiveDayForecast.collectAsStateWithLifecycle()

    var showSearch by remember { mutableStateOf(false) }

//
//    LaunchedEffect(Unit) {
//
//    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "WeatherApp",
                    )
                },
                actions = {
                    IconButton(onClick = { showSearch = true }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = modifier
                    .fillMaxSize()
//                    .background(MaterialTheme.colorScheme.background)
//                    .background(MaterialTheme.colorScheme.primary)
                    .padding(it)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                // Search Overlay UI
                if (showSearch){
                    CitySearchUi(
                        onCitySelected = { selectedCity ->
                            showSearch = false
                            viewModel.fetchCityWeather(selectedCity)
                            viewModel.fetchCityForeCast(selectedCity)
                        },
                        onDismiss = { showSearch = false }
                    )
                } else{
                    // Weather details
                    /*Column(
                        modifier = modifier.fillMaxWidth()
//                            .fillMaxHeight(0.6f)
                        .weight(1f, true),
                    ) {

                        when(weatherUiState){

                            is WeatherUiState.Idle -> {
                                CityWeatherIdle()
                            }
                            is WeatherUiState.Loading -> {
                                CityWeatherLoadingUi()
                            }
                            is WeatherUiState.Success -> {
                                CityWeatherUi(
                                    weatherData = weatherData,
                                )
                            }
                            is WeatherUiState.Error -> {
                                CityWeatherErrorUi(
                                    errorMessage = viewModel.errorMessage.value.toString(),
                                    onRetry = {
                                        showSearch = true
                                    }
                                )
                            }
                        }

                    }*/


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
                                ForecastUi(
                                    forecastData = forecastData
                                )
                            }
                            is ForecastUiState.Error -> {
                                CityWeatherErrorUi(
                                    errorMessage = "Could not load forecast data.",
                                    onRetry = {}
                                )
                            }
                        }
                    }


                    Text(
                        text = UiUtils.formatLastUpdated(weatherData?.dt),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        },
        bottomBar = {}
    )
}