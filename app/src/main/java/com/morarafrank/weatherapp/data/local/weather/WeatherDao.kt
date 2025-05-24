package com.morarafrank.weatherapp.data.local.weather

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao{

    @Insert
    suspend fun insertWeather(weather: LocalWeather)

    @Delete
    suspend fun deleteWeather(weather: LocalWeather)

    @Query("SELECT * FROM localweather")
    suspend fun getWeather(): Flow<List<LocalWeather>>
}