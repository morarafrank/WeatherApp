package com.morarafrank.weatherapp.data.local.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localweather")
data class LocalWeather(
    @PrimaryKey val id: Int,
    val main: String,
    val description: String,
    val icon: String
)