package com.morarafrank.weatherapp.domain.model

data class WeatherResponse(
    val coord: Coord,
    val weather: List<RemoteWeather>,
    val base: String,
    val main: MainWeather,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val id: Long,
    val name: String,
    val cod: Int
)
