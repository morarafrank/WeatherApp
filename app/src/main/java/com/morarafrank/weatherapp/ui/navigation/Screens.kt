package com.morarafrank.weatherapp.ui.navigation

sealed class Screens(val route: String) {

    object SplashScreen : Screens("splash")
    object WeatherDetailsScreen : Screens("weather_details")

}