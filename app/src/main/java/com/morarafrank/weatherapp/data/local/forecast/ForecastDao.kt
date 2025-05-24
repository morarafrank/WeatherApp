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

    @Delete
    suspend fun deleteForecast(forecast: LocalForecast)

    @Query("SELECT * FROM localforecast")
    suspend fun getForecastFromLocal(): Flow<List<LocalForecast>>
}