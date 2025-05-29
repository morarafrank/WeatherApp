package com.morarafrank.weatherapp.data.repo

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import com.morarafrank.weatherapp.data.local.forecast.ForecastDao
import com.morarafrank.weatherapp.data.local.forecast.LocalForecast
import com.morarafrank.weatherapp.data.local.weather.LocalWeather
import com.morarafrank.weatherapp.data.local.weather.WeatherDao
import com.morarafrank.weatherapp.data.remote.WeatherService
import com.morarafrank.weatherapp.domain.mappers.toForecastItem
import com.morarafrank.weatherapp.domain.mappers.toLocalForecast
import com.morarafrank.weatherapp.domain.mappers.toLocalWeatherEntity
import com.morarafrank.weatherapp.domain.mappers.toWeatherResponse
import com.morarafrank.weatherapp.domain.model.Clouds
import com.morarafrank.weatherapp.domain.model.ForecastItem
import com.morarafrank.weatherapp.domain.model.ForecastResponse
import com.morarafrank.weatherapp.domain.model.MainForecast
import com.morarafrank.weatherapp.domain.model.RemoteWeather
import com.morarafrank.weatherapp.domain.model.Sys
import com.morarafrank.weatherapp.domain.model.WeatherResponse
import com.morarafrank.weatherapp.domain.model.Wind
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException


class WeatherRepository @Inject constructor(
    private val weatherRemoteDataSource: WeatherService,
    private val weatherDao: WeatherDao,
    private val forecastDao: ForecastDao
) {


    /** Weather **/

    suspend fun getWeather(city: String): WeatherResponse {
        return try {

            val response = weatherRemoteDataSource.getCurrentWeather(city)

            // convert API response to local entity and save to Room
            val localWeather = response.toLocalWeatherEntity()
            weatherDao.insertWeather(localWeather)

            Log.i("WeatherRepository", "Weather fetched from API: $response")
            // Return weather from API response
            return response

        } catch (e: Exception) {

            // On error, try to fetch cached entity from Room
            val cachedList = weatherDao.getAllLocalWeather().first()
            val cached = cachedList.firstOrNull { it.cityName.equals(city, ignoreCase = true) }

            if (cached == null) {
                throw e
            }

            // Convert cached Room entity back to API response format
            Log.e("WeatherRepository", "Error fetching weather from API: ${e.message}", e)
            Log.i("WeatherRepository", "Returning cached weather: $cached")
            cached.toWeatherResponse()
        }
    }

    /**Forecast **/

    suspend fun getFiveDayForecast(city: String): List<ForecastItem> {
        return try {
            val response = weatherRemoteDataSource.getFiveDayForecast(city)

            Log.i("WeatherRepository", "Forecast fetched from API: $response")

            // Save each ForcastItem as a LocalForecast
            response.list.forEach { item ->
                // Convert ForecastItem to LocalForecast and save to Room
                val localForecast = item.toLocalForecast(response.city.name)
                Log.d("WeatherRepository", "Saving forecast to DB: $localForecast")
                forecastDao.addForecast(localForecast)
            }

            response.list
        } catch (e: Exception) {
            Log.e("WeatherRepository", "Error fetching forecast from API: ${e.message}", e)

            val cachedForecasts = forecastDao.getForecastFromLocal(city)

            Log.i("WeatherRepository", "Returning cached forecasts: $cachedForecasts")

            if (cachedForecasts.isEmpty()) {
                Log.i("WeatherRepository", "No cached forecasts found for city: $city")
                throw e
            }

            Log.i(WeatherRepository::class.java.simpleName, "Converting cached forecasts to ForecastItem list")

            // map cached LocalForecasts back to ForecastItems
            cachedForecasts.map {
                it.toForecastItem()
            }
        }
    }



}