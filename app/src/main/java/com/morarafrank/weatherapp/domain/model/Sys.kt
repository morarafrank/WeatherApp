package com.morarafrank.weatherapp.domain.model

data class Sys(
    val type: Int?,
    val id: Int?,
//    val message: Double,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)