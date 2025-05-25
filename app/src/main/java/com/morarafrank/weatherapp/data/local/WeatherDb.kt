package com.morarafrank.weatherapp.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.morarafrank.weatherapp.data.local.forecast.LocalForecast
import com.morarafrank.weatherapp.data.local.forecast.ForecastDao
import com.morarafrank.weatherapp.data.local.weather.LocalWeather
import com.morarafrank.weatherapp.data.local.weather.WeatherDao
import com.morarafrank.weatherapp.utils.Converters

@Database(
    entities = [LocalWeather::class, LocalForecast::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    abstract fun forecastDao(): ForecastDao
}
