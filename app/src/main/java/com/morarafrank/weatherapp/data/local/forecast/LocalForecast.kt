package com.morarafrank.weatherapp.data.local.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.morarafrank.weatherapp.domain.model.City
import com.morarafrank.weatherapp.domain.model.Clouds
import com.morarafrank.weatherapp.domain.model.ForecastItem
import com.morarafrank.weatherapp.domain.model.MainForecast
import com.morarafrank.weatherapp.domain.model.Wind

@Entity(tableName = "localforecasts")
data class LocalForecast(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // ciytName
    val cityName: String,

    //forecast items
    val dt: Long,
    val temp: Double,
    val feelsLike: Double,
    val main: String,
    val description: String,
    val icon: String

)