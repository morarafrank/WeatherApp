package com.morarafrank.weatherapp.ui.state

import com.morarafrank.weatherapp.domain.model.WeatherResponse

sealed class WeatherUiState {
    object Idle : WeatherUiState()
    object Loading : WeatherUiState()
    data class Success(val data: WeatherResponse) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}
