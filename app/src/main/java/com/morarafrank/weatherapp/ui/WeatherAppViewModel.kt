package com.morarafrank.weatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morarafrank.weatherapp.data.local.weather.LocalWeather
import com.morarafrank.weatherapp.data.repo.WeatherRepository
import com.morarafrank.weatherapp.ui.state.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherAppViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    private val TAG = "WeatherAppViewModel"

    private val _weatherState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val weatherState: StateFlow<WeatherUiState> = _weatherState.asStateFlow()

    private val _localWeatherList = MutableStateFlow<List<LocalWeather>>(emptyList())
    val localWeatherList: StateFlow<List<LocalWeather>> = _localWeatherList.asStateFlow()

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            _weatherState.value = WeatherUiState.Loading
            try {
                val response = weatherRepository.getWeather(city)
                _weatherState.value = WeatherUiState.Success(response)
            } catch (e: Exception) {
                _weatherState.value = WeatherUiState.Error("Could not fetch weather: ${e.localizedMessage}")
            }
        }
    }

    fun getCachedWeather() {
        viewModelScope.launch {
//            val cached = weatherRepository.getCachedWeather()
//            _localWeatherList.value = cached
        }
    }

    fun deleteLocalWeather(weather: LocalWeather) {
        viewModelScope.launch {
            weatherRepository.deleteWeatherFromLocal(weather)
            getCachedWeather() // Refresh the list
        }
    }


}