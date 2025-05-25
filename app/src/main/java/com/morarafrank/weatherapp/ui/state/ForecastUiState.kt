package com.morarafrank.weatherapp.ui.state

import com.morarafrank.weatherapp.domain.model.ForecastItem
import com.morarafrank.weatherapp.domain.model.ForecastResponse
import com.morarafrank.weatherapp.domain.model.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class ForecastUiState {
    object Idle : ForecastUiState()
    object Loading : ForecastUiState()
    data class Success(val data: List<ForecastItem>) : ForecastUiState()
    data class Error(val message: String) : ForecastUiState()
}
