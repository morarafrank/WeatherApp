package com.morarafrank.weatherapp.domain.use_cases

import com.morarafrank.weatherapp.data.local.forecast.LocalForecast
import com.morarafrank.weatherapp.data.repo.WeatherRepository
import javax.inject.Inject

class GetForecastFromLocalDataSource @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

//    suspend operator fun invoke(cityName: String): List<LocalForecast> {
//        return weatherRepository.getForecastByCityName(cityName)
//    }
}
