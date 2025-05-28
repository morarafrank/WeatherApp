package com.morarafrank.weatherapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.morarafrank.weatherapp.R
import com.morarafrank.weatherapp.ui.WeatherAppViewModel
import com.morarafrank.weatherapp.ui.screens.composables.CitySearchUi
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
    modifier: Modifier = Modifier,
    viewModel: WeatherAppViewModel = hiltViewModel(),
) {

    val weatherUiState by viewModel.weatherUiState.collectAsStateWithLifecycle()
    val forecastUiState by viewModel.forecastUiState.collectAsStateWithLifecycle()

    val weatherData = viewModel.weatherData.value
    val forecastData by viewModel.fiveDayForecast.collectAsStateWithLifecycle()

    var showSearch by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = !showSearch
            ) {
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
            }
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    if (showSearch) {
                        CitySearchUi(
                            onCitySelected = { selectedCity ->
                                showSearch = false
                                viewModel.fetchCityWeather(selectedCity)
                                viewModel.fetchCityForeCast(selectedCity)
                            },
                            onDismiss = { showSearch = false }
                        )
                    } else {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                        ) {
                            when (weatherUiState) {
                                is WeatherUiState.Idle -> CityWeatherIdle()
                                is WeatherUiState.Loading ->
                                    CityWeatherLoadingUi(
                                    size = 200.dp,
                                    title = "Loading weather data...",
                                    animation = R.raw.loading_anim0
                                )
                                is WeatherUiState.Success -> CityWeatherUi(weatherData = weatherData)
                                is WeatherUiState.Error -> CityWeatherErrorUi(
                                    errorMessage = viewModel.errorMessage.value.toString(),
                                    onRetry = { showSearch = true }
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            when (forecastUiState) {
                                is ForecastUiState.Idle -> EmptyForecastUi("No forecast data available.")
                                is ForecastUiState.Loading -> CityWeatherLoadingUi(
                                    size = 200.dp,
                                    title = "Loading forecast data...",
                                    animation = R.raw.loading_anim0
                                )
                                is ForecastUiState.Success -> ForecastUi(forecastData = forecastData)
                                is ForecastUiState.Error -> CityWeatherErrorUi(
                                    errorMessage = "Could not load forecast data.",
                                    onRetry = {
                                        showSearch = true
                                    }
                                )
                            }
                        }
                    }
                }
            }
        },

    bottomBar = {}
    )
}