package com.morarafrank.weatherapp.domain.use_cases

import com.morarafrank.weatherapp.data.local.weather.LocalWeather
import com.morarafrank.weatherapp.data.repo.WeatherRepository
import javax.inject.Inject

class GetWeatherFromLocalUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

//    suspend operator fun invoke(cityName: String): LocalWeather? {
//        return weatherRepository.getLocalWeatherByCityName(cityName)
//    }

    suspend operator fun invoke(): List<LocalWeather> {
        return weatherRepository.getAllLocalWeather()
    }
}