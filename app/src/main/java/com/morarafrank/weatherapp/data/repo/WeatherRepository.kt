package com.morarafrank.weatherapp.data.repo

import androidx.compose.runtime.toMutableStateList
import com.morarafrank.weatherapp.data.local.forecast.ForecastDao
import com.morarafrank.weatherapp.data.local.forecast.LocalForecast
import com.morarafrank.weatherapp.data.local.weather.LocalWeather
import com.morarafrank.weatherapp.data.local.weather.WeatherDao
import com.morarafrank.weatherapp.data.remote.WeatherService
import com.morarafrank.weatherapp.domain.mappers.toLocalForecast
import com.morarafrank.weatherapp.domain.mappers.toLocalWeatherEntity
import com.morarafrank.weatherapp.domain.mappers.toWeatherResponse
import com.morarafrank.weatherapp.domain.model.ForecastItem
import com.morarafrank.weatherapp.domain.model.ForecastResponse
import com.morarafrank.weatherapp.domain.model.WeatherResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject


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
            cached.toWeatherResponse()
        }
    }

//    suspend fun getAllLocalWeather(): List<LocalWeather> {
//        return weatherDao.getAllLocalWeather()
//    }

    suspend fun getLocalWeatherByCityName(cityName: String): LocalWeather? {
        return weatherDao.getLocalWeatherByCityName(cityName)
    }

    suspend fun deleteWeatherFromLocal(weather: LocalWeather) {
        weatherDao.deleteWeather(weather)
    }


//    suspend fun getCachedWeather(): List<LocalWeather> {
//        return weatherDao.getAllLocalWeather()
//    }





    /**Forecast **/

//    suspend fun getFiveDayForecastFromRemoteSource(city: String) =
//        weatherRemoteDataSource.getFiveDayForecast(city)

    suspend fun getFiveDayForecastFromRemoteSource(city: String): List<ForecastItem> {
        return try {
            val response = weatherRemoteDataSource.getFiveDayForecast(city)

            val limitedForecastItems = response.list.take(6)

            // Map response to LocalForecast with limited forecasts and save to room
            val localForecast = LocalForecast(
                city = response.city,
                forecasts = limitedForecastItems
            )

            forecastDao.addForecast(localForecast)

            limitedForecastItems
        } catch (e: Exception) {
            // Log or rethrow
            throw e
        }
    }


    suspend fun deleteAllForecastsFromLocal() {
        forecastDao.deleteAllForecasts()
    }

    fun getForecastsFromLocal(cityName: String)
    = forecastDao.getForecastFromLocal(cityName)

}