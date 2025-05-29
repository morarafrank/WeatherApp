package com.morarafrank.weatherapp.data.local.forecast

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addForecast(forecast: LocalForecast)

    // select all forecasts for a city with cityname
    @Query("SELECT * FROM localforecasts WHERE LOWER(cityName) = LOWER(:cityName)")
    suspend fun getForecastFromLocal(cityName: String): List<LocalForecast>

}