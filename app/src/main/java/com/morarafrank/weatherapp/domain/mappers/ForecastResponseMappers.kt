package com.morarafrank.weatherapp.domain.mappers

import com.morarafrank.weatherapp.data.local.forecast.LocalForecast
import com.morarafrank.weatherapp.domain.model.ForecastResponse

fun ForecastResponse.toLocalForecast(limit: Int): LocalForecast {
    return LocalForecast(
        city = this.city,
        forecasts = this.list.take(limit) // only take `limit` number of forecasts
    )
}

