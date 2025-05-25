package com.morarafrank.weatherapp.data.repo

import com.morarafrank.weatherapp.data.local.forecast.ForecastDao
import com.morarafrank.weatherapp.data.local.forecast.LocalForecast
import com.morarafrank.weatherapp.data.local.weather.LocalWeather
import com.morarafrank.weatherapp.data.local.weather.WeatherDao
import com.morarafrank.weatherapp.data.remote.WeatherService
import com.morarafrank.weatherapp.domain.mappers.toLocalWeatherEntity
import com.morarafrank.weatherapp.domain.mappers.toWeatherResponse
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
            response

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

    suspend fun getFiveDayForecastFromRemoteSource(city: String) =
        weatherRemoteDataSource.getFiveDayForecast(city)

    suspend fun saveForecastToLocal(forecast: LocalForecast) {
        forecastDao.addForecast(forecast)
    }

    suspend fun deleteForecastFromLocal(forecast: LocalForecast) {
        forecastDao.deleteForecast(forecast)
    }

    suspend fun getForecastFromLocal() = forecastDao.getForecastFromLocal()

}