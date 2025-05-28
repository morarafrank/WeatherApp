package com.morarafrank.weatherapp.domain.model

data class ForecastItem(
    val dt: Long,
    val main: MainForecast,
    val weather: List<RemoteWeather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val rain: Rain?,
    val sys: Sys,
    val dt_txt: String
)