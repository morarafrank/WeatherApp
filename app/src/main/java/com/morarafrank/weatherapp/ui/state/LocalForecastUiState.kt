package com.morarafrank.weatherapp.ui.state

import com.morarafrank.weatherapp.data.local.forecast.LocalForecast

sealed class LocalForecastUiState {
    object Idle : LocalForecastUiState()
    object Loading : LocalForecastUiState()
    data class Success(val data: List<LocalForecast>) : LocalForecastUiState()
    data class Error(val message: String) : LocalForecastUiState()
}

