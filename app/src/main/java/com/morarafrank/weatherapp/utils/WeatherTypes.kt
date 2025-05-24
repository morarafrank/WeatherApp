package com.morarafrank.weatherapp.utils

enum class WeatherTypes(val type: String) {
    SUNNY("sunny"),
    CLOUDY("cloudy"),
    RAINY("rainy"),
    SNOWY("snowy"),
    STORMY("stormy"),
    WINDY("windy"),
    FOGGY("foggy"),
    HAZY("hazy"),
    THUNDERSTORM("thunderstorm");

    companion object {
        fun fromString(type: String): WeatherTypes? {
            return values().find { it.type == type }
        }
    }
}