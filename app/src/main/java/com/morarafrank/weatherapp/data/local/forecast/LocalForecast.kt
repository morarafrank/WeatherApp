package com.morarafrank.weatherapp.data.local.forecast

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.morarafrank.weatherapp.domain.model.City
import com.morarafrank.weatherapp.domain.model.ForecastItem

@Entity(tableName = "localforecast")
data class LocalForecast(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val city: City,
    val forecasts: List<ForecastItem>
)