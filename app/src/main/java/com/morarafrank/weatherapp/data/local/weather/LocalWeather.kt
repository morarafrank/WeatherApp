package com.morarafrank.weatherapp.data.local.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localweather")
data class LocalWeather(

    // city id
    @PrimaryKey val id: Long,

    val cityName: String,
    val base: String,
    val visibility: Int,
    val dt: Long,
    val timezone: Int,
    val cod: Int,
    val lastUpdated: Long, // timestamp when stored

    // Coord
    val coordLon: Double,
    val coordLat: Double,

    // Weather
    val weatherId: Int,
    val weatherMain: String,
    val weatherDescription: String,
    val weatherIcon: String,

    // Main
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
    val seaLevel: Int?,
    val grndLevel: Int?,

    // Wind
    val windSpeed: Double,
    val windDeg: Int,

    // Clouds
    val cloudsAll: Int,

    // Sys
    val sysType: Int?,
    val sysId: Int?,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)