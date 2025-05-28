package com.morarafrank.weatherapp.domain.mappers

import com.morarafrank.weatherapp.data.local.weather.LocalWeather
import com.morarafrank.weatherapp.domain.model.Clouds
import com.morarafrank.weatherapp.domain.model.Coord
import com.morarafrank.weatherapp.domain.model.MainWeather
import com.morarafrank.weatherapp.domain.model.RemoteWeather
import com.morarafrank.weatherapp.domain.model.Sys
import com.morarafrank.weatherapp.domain.model.WeatherResponse
import com.morarafrank.weatherapp.domain.model.Wind

fun WeatherResponse.toLocalWeatherEntity(): LocalWeather {
    val weather = this.weather.firstOrNull()

    return LocalWeather(
        id = this.id,
        cityName = this.name,
        base = this.base,
        visibility = this.visibility,
        dt = this.dt,
        timezone = this.timezone,
        cod = this.cod,
        lastUpdated = System.currentTimeMillis(),

        coordLon = this.coord.lon,
        coordLat = this.coord.lat,

        weatherId = weather?.id ?: 0,
        weatherMain = weather?.main ?: "",
        weatherDescription = weather?.description ?: "",
        weatherIcon = weather?.icon ?: "",

        temp = this.main.temp,
        feelsLike = this.main.feels_like,
        tempMin = this.main.temp_min,
        tempMax = this.main.temp_max,
        pressure = this.main.pressure,
        humidity = this.main.humidity,
        seaLevel = this.main.sea_level,
        grndLevel = this.main.grnd_level,

        windSpeed = this.wind.speed,
        windDeg = this.wind.deg,

        cloudsAll = this.clouds.all,

        sysType = this.sys.type,
        sysId = this.sys.id,
        country = this.sys.country,
        sunrise = this.sys.sunrise,
        sunset = this.sys.sunset
    )
}

fun LocalWeather.toWeatherResponse(): WeatherResponse {
    return WeatherResponse(
        coord = Coord(
            lon = this.coordLon,
            lat = this.coordLat
        ),
//        weather = listOf(
//            RemoteWeather(
//                id = this.weatherId,
//                main = this.weatherMain,
//                description = this.weatherDescription,
//                icon = this.weatherIcon
//            )
//        ),
        weather =
            listOf(
            RemoteWeather(
                id = this.weatherId,
                main = this.weatherMain,
                description = this.weatherDescription,
                icon = this.weatherIcon
            )
        ),
        base = "local",
        main = MainWeather(
            temp = this.temp,
            feels_like = this.feelsLike,
            temp_min = this.tempMin,
            temp_max = this.tempMax,
            pressure = this.pressure,
            humidity = this.humidity,
            sea_level = this.seaLevel,
            grnd_level = this.grndLevel
        ),
        visibility = this.visibility,
        wind = Wind(
            speed = this.windSpeed,
            deg = this.windDeg
        ),
        clouds = Clouds(
            all = this.cloudsAll
        ),
        dt = this.dt,
        sys = Sys(
            type = this.sysType,
            id = this.sysId,
            country = this.country,
            sunrise = this.sunrise,
            sunset = this.sunset,
//            message = this.
        ),
        id = this.id,
        name = this.cityName,
        cod = this.cod,
        timezone = this.timezone
    )
}
