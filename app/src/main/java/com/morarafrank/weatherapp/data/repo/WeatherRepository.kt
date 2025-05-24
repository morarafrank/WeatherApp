package com.morarafrank.weatherapp.data.repo

import com.morarafrank.weatherapp.data.local.forecast.LocalForecast
import com.morarafrank.weatherapp.data.local.forecast.ForecastDao
import com.morarafrank.weatherapp.data.local.weather.LocalWeather
import com.morarafrank.weatherapp.data.local.weather.WeatherDao
import com.morarafrank.weatherapp.data.remote.WeatherService
import com.morarafrank.weatherapp.domain.model.WeatherResponse
import javax.inject.Inject


class WeatherRepository @Inject constructor(
    private val weatherRemoteDataSource: WeatherService,
    private val weatherDao: WeatherDao,
    private val forecastDao: ForecastDao
) {

    suspend fun getCurrentWeatherFromRemoteSource(city: String) =
        weatherRemoteDataSource.getCurrentWeather(city)

    suspend fun getFiveDayForecastFromRemoteSource(city: String) =
        weatherRemoteDataSource.getFiveDayForecast(city)


    // Weather Local Data Source.
    suspend fun saveWeatherToLocal(weather: LocalWeather) {
        weatherDao.insertWeather(weather)
    }

    suspend fun deleteWeatherFromLocal(weather: LocalWeather) {
        weatherDao.deleteWeather(weather)
    }

    suspend fun getWeatherFromLocal() = weatherDao.getWeather()

    // Forecast Local Data Source.

    suspend fun saveForecastToLocal(forecast: LocalForecast) {
        forecastDao.addForecast(forecast)
    }

    suspend fun deleteForecastFromLocal(forecast: LocalForecast) {
        forecastDao.deleteForecast(forecast)
    }

    suspend fun getForecastFromLocal() = forecastDao.getForecastFromLocal()

}