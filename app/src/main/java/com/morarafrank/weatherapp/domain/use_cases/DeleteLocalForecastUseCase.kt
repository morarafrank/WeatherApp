package com.morarafrank.weatherapp.domain.use_cases

import com.morarafrank.weatherapp.data.repo.WeatherRepository
import javax.inject.Inject

class DeleteLocalForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

//    suspend operator fun invoke(forecastId: Long) {
//        weatherRepository.deleteForecastFromLocal(forecastId)
//    }

}