package com.morarafrank.weatherapp.domain.use_cases

import com.morarafrank.weatherapp.data.local.weather.LocalWeather
import com.morarafrank.weatherapp.data.repo.WeatherRepository
import javax.inject.Inject

class DeleteLocalWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(weather: LocalWeather) {
        weatherRepository.deleteWeatherFromLocal(weather)
    }
}
