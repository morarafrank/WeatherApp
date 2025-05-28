package com.morarafrank.weatherapp.domain.use_cases

import com.morarafrank.weatherapp.data.repo.WeatherRepository
import com.morarafrank.weatherapp.domain.model.WeatherResponse
import javax.inject.Inject

class GetWeatherFromRemoteDataSourceUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(cityName: String): WeatherResponse {
        return weatherRepository.getWeather(cityName)
    }

}