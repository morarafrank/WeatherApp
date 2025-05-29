package com.morarafrank.weatherapp.domain.mappers

import com.morarafrank.weatherapp.data.local.forecast.LocalForecast
import com.morarafrank.weatherapp.domain.model.Clouds
import com.morarafrank.weatherapp.domain.model.ForecastItem
import com.morarafrank.weatherapp.domain.model.ForecastResponse
import com.morarafrank.weatherapp.domain.model.MainForecast
import com.morarafrank.weatherapp.domain.model.RemoteWeather
import com.morarafrank.weatherapp.domain.model.Sys
import com.morarafrank.weatherapp.domain.model.Wind


fun ForecastItem.toLocalForecast(cityName: String): LocalForecast {
    return LocalForecast(
        cityName = cityName,
        dt = this.dt,
        temp = this.main.temp,
        feelsLike = this.main.feels_like,
        main = this.weather.firstOrNull()?.main ?: "",
        description = this.weather.firstOrNull()?.description ?: "",
        icon = this.weather.firstOrNull()?.icon ?: ""
    )
}


fun LocalForecast.toForecastItem(): ForecastItem {
    return ForecastItem(
        dt = this.dt,
        main = MainForecast(
            temp = this.temp,
            feels_like = this.feelsLike,
            temp_min = 0.0,     // defaults
            temp_max = 0.0,
            pressure = 0,
            humidity = 0,
            sea_level = 0,
            grnd_level = 0,
            temp_kf = 0.0
        ),
        weather = listOf(
            RemoteWeather(
                id = 0,               // default, since not stored
                main = this.main,
                description = this.description,
                icon = this.icon
            )
        ),
        clouds = Clouds(all = 0),     // default
        wind = Wind(speed = 0.0, deg = 0),
        visibility = 0,
        pop = 0.0,
        rain = null,
        sys = Sys(
            country = "",
            sunrise = 0,
            sunset = 0,
            type = 0,
            id = 0
        ),
        dt_txt = this.dt.toString()
    )
}
