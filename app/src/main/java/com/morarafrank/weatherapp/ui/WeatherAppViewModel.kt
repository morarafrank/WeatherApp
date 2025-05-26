package com.morarafrank.weatherapp.ui

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.runtime.State
import com.morarafrank.weatherapp.data.local.forecast.LocalForecast
import com.morarafrank.weatherapp.domain.model.ForecastItem
import com.morarafrank.weatherapp.domain.model.WeatherResponse
import com.morarafrank.weatherapp.ui.state.ForecastUiState
import com.morarafrank.weatherapp.ui.state.UiEvent
import com.morarafrank.weatherapp.utils.WeatherSharedPrefs
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class WeatherAppViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val sharedPrefs: WeatherSharedPrefs
): ViewModel() {

    private val TAG = "WeatherAppViewModel"

    private val _weatherUiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val weatherUiState: StateFlow<WeatherUiState> = _weatherUiState.asStateFlow()


    private val _forecastUiState = MutableStateFlow<ForecastUiState>(ForecastUiState.Idle)
    val forecastUiState: StateFlow<ForecastUiState> = _forecastUiState.asStateFlow()

    private val _localWeatherList = MutableStateFlow<List<LocalWeather>>(emptyList())
    val localWeatherList: StateFlow<List<LocalWeather>> = _localWeatherList.asStateFlow()

    // Current weather data
    private val _weatherData = mutableStateOf<WeatherResponse?>(null)
    val weatherData: State<WeatherResponse?> = _weatherData

    // five day forecast
    private val _fiveDayForecast = MutableStateFlow<List<ForecastItem>>(emptyList())
    val fiveDayForecast: StateFlow<List<ForecastItem>> = _fiveDayForecast.asStateFlow()

    private val _query = mutableStateOf("")
    val query: State<String> = _query

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }

    private val _selectedCity = mutableStateOf<String?>(null)
    val selectedCity: State<String?> = _selectedCity

    init {
        initializeCityWeather()
    }

    fun initializeCityWeather() {
        val savedCity = sharedPrefs.getCity()
        savedCity?.let { searchCity(it, saveToPrefs = false) }
    }

    fun onCitySearched(city: String) {

        sharedPrefs.clearWeatherSharedPrefs()
//            .apply {
//            searchCity(city, saveToPrefs = false) }
        searchCity(city, saveToPrefs = true)
    }

    private fun searchCity(city: String, saveToPrefs: Boolean) {
        _selectedCity.value = city

        if (saveToPrefs) {
            sharedPrefs.saveCity(city)
        }
        Log.i(TAG, "Searching for city: $city")

        viewModelScope.launch {
            fetchCityWeather(city)
//            fetchCityForeCast(city)
        }
    }

    fun refreshData() {
        _selectedCity.value?.let { city ->
            viewModelScope.launch {
                try {
                    fetchCityWeather(city)
                    fetchCityForeCast(city)
                } catch (e: Exception) {
                    _uiEvent.emit(UiEvent.ShowSnackbar("Refresh failed. Please check your connection."))
                }
            }
        }
    }
    fun clearWeatherData() {
        _weatherData.value = null
        _fiveDayForecast.value = emptyList()
        _weatherUiState.value = WeatherUiState.Idle
        _forecastUiState.value = ForecastUiState.Idle
        _selectedCity.value = null
        sharedPrefs.clearWeatherSharedPrefs()
    }
    fun fetchCityWeather(city: String) {
        viewModelScope.launch {
            _weatherUiState.value = WeatherUiState.Loading

            try {
                val response = weatherRepository.getWeather(city)
                if (response.cod == 200) {
                    _weatherUiState.value = WeatherUiState.Success(response)
                    _weatherData.value = response
                    Log.d(TAG, "Weather data fetched successfully: $response")

                    // Fetch the five-day forecast
//                    _fiveDayForecast.value = weatherRepository.getFiveDayForecastFromRemoteSource(city)
                } else {
                    _weatherUiState.value = WeatherUiState.Error("Error fetching weather: $response")
                    Log.e(TAG, "Error fetching weather: $response")
                }
            } catch (e: Exception) {
                _weatherUiState.value = WeatherUiState.Error("Could not fetch weather: ${e.localizedMessage}")
                Log.e(TAG, "Could not fetch weather: ${e.localizedMessage}", e)
            }
        }
    }

    fun fetchCityForeCast(city: String) {
        viewModelScope.launch {
            _forecastUiState.value = ForecastUiState.Loading
            try {
                val forecast = weatherRepository.getFiveDayForecastFromRemoteSource(city)

                if (forecast.isNotEmpty()) {
                    _fiveDayForecast.value = forecast
                    _forecastUiState.value = ForecastUiState.Success(forecast)
                    Log.d(TAG, "Forecast data fetched successfully for $city: $forecast")
                } else {
                    _weatherUiState.value = WeatherUiState.Error("No forecast data available for $city")
                    _forecastUiState.value = ForecastUiState.Error("No forecast data available for $city")
                    Log.e(TAG, "No forecast data available for $city")
                }
            } catch (e: Exception) {
                _weatherUiState.value = WeatherUiState.Error("Could not fetch forecast: ${e.localizedMessage}")
                _forecastUiState.value = ForecastUiState.Error("Could not fetch forecast: ${e.localizedMessage}")
                Log.e(TAG, "Could not fetch forecast: ${e.localizedMessage}", e)
            }
        }
    }


}