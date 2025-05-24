package com.morarafrank.weatherapp.domain.model

data class MainWeather(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val temp_min: Double,
    val temp_max: Double
)