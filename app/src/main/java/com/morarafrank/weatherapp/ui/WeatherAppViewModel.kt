package com.morarafrank.weatherapp.ui

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

    private val _weatherState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val weatherState: StateFlow<WeatherUiState> = _weatherState.asStateFlow()


    private val _forecastState = MutableStateFlow<ForecastUiState>(ForecastUiState.Idle)
    val forecastState: StateFlow<ForecastUiState> = _forecastState.asStateFlow()

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

    private val allCities = listOf("Nairobi", "New York", "Tokyo", "Cairo", "Lagos")

    val filteredCities: State<List<String>> = derivedStateOf {
        if (_query.value.isBlank()) allCities
        else allCities.filter { it.contains(_query.value, ignoreCase = true) }
    }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }

    private val _selectedCity = mutableStateOf<String?>(null)
    val selectedCity: State<String?> = _selectedCity

    init {
        initializeCityWeather()
    }

    fun fetchCityWeather(city: String) {
        viewModelScope.launch {
            _weatherState.value = WeatherUiState.Loading

            try {
                val response = weatherRepository.getWeather(city)
                if (response.cod == 200) {
                    _weatherState.value = WeatherUiState.Success(response)
                    _weatherData.value = response

                    // Fetch the five-day forecast
                    _fiveDayForecast.value = weatherRepository.getFiveDayForecastFromRemoteSource(city)
                } else {
                    _weatherState.value = WeatherUiState.Error("Error fetching weather: $response")
                }
            } catch (e: Exception) {
                _weatherState.value = WeatherUiState.Error("Could not fetch weather: ${e.localizedMessage}")
            }
        }
    }

    fun fetchCityForeCast(city: String) {
        viewModelScope.launch {
            _forecastState.value = ForecastUiState.Loading
            try {
                val forecast = weatherRepository.getFiveDayForecastFromRemoteSource(city)

                if (forecast.isNotEmpty()) {
                    _fiveDayForecast.value = forecast
                    _forecastState.value = ForecastUiState.Success(forecast)
                } else {
                    _weatherState.value = WeatherUiState.Error("No forecast data available for $city")
                    _forecastState.value = ForecastUiState.Error("No forecast data available for $city")
                }
            } catch (e: Exception) {
                _weatherState.value = WeatherUiState.Error("Could not fetch forecast: ${e.localizedMessage}")
                _forecastState.value = ForecastUiState.Error("Could not fetch forecast: ${e.localizedMessage}")
            }
        }
    }

    fun initializeCityWeather() {
        val savedCity = sharedPrefs.getCity()
        savedCity?.let { selectCity(it, saveToPrefs = false) }
    }

    fun onCitySelected(city: String) {

        sharedPrefs.clearWeatherSharedPrefs()
//            .apply {
//            selectCity(city, saveToPrefs = false) }
        selectCity(city, saveToPrefs = true)
    }

    private fun selectCity(city: String, saveToPrefs: Boolean) {
        _selectedCity.value = city

        if (saveToPrefs) {
            sharedPrefs.saveCity(city)
        }

        viewModelScope.launch {
            fetchCityWeather(city)
            fetchCityForeCast(city)
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


}