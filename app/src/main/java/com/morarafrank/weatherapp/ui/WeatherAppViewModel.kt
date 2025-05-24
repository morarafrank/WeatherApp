package com.morarafrank.weatherapp.ui

import androidx.lifecycle.ViewModel
import com.morarafrank.weatherapp.data.repo.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherAppViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    private val TAG = "WeatherAppViewModel"

    suspend fun fetchCurrentWeatherFromRemote(city: String) {
        weatherRepository.getCurrentWeatherFromRemoteSource(city)
    }

    // fetch five day forecast from remote source

    suspend fun fetchFiveDayForecastFromRemote(city: String) {
        weatherRepository.getFiveDayForecastFromRemoteSource(city)
    }

    // fetch current weather from local source
    suspend fun fetchCurrentWeatherFromLocal() = weatherRepository.getWeatherFromLocal()


    // fetch five day forecast from local source
    suspend fun fetchFiveDayForecastFromLocal() = weatherRepository.getForecastFromLocal()

    // refresh local weather data in local database
    suspend fun deleteWeatherFromLocal(weather: com.morarafrank.weatherapp.data.local.weather.LocalWeather) {
        weatherRepository.deleteWeatherFromLocal(weather)
    }

    suspend fun deleteForecastFromLocal(forecast: com.morarafrank.weatherapp.data.local.forecast.LocalForecast) {
        weatherRepository.deleteForecastFromLocal(forecast)
    }

}