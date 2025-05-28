package com.morarafrank.weatherapp.data.local.weather

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: LocalWeather)

    @Delete
    suspend fun deleteWeather(weather: LocalWeather)

    @Query("SELECT * FROM localweather")
    fun getAllLocalWeather(): Flow<List<LocalWeather>>

    @Query("SELECT * FROM localweather WHERE cityName = :cityName COLLATE NOCASE LIMIT 1")
    suspend fun getLocalWeatherByCityName(cityName: String): LocalWeather?

}