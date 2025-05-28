package com.morarafrank.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("3h")
    val volume: Double
)