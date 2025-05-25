package com.morarafrank.weatherapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.morarafrank.weatherapp.domain.model.RemoteWeather

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromWeatherList(value: List<RemoteWeather>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toWeatherList(value: String): List<RemoteWeather> {
        val listType = object : TypeToken<List<RemoteWeather>>() {}.type
        return gson.fromJson(value, listType)
    }
}
