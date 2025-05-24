package com.morarafrank.weatherapp.data.local.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.morarafrank.weatherapp.domain.model.Clouds
import com.morarafrank.weatherapp.domain.model.MainForecast
import com.morarafrank.weatherapp.domain.model.RemoteWeather
import com.morarafrank.weatherapp.domain.model.Wind

@Entity(tableName = "localforecast")
data class LocalForecast(
    @PrimaryKey val id: Int = 0,
    val dt: Long,
    val main: MainForecast,
    val weather: List<RemoteWeather>,
    val clouds: Clouds,
    val wind: Wind,
    val dt_txt: String
)