package com.morarafrank.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.morarafrank.weatherapp.ui.navigation.Screens.WeatherDetailsScreen
import com.morarafrank.weatherapp.ui.screens.SearchCityScreen
import com.morarafrank.weatherapp.ui.screens.SplashScreen
import com.morarafrank.weatherapp.ui.screens.WeatherDetailsScreen

@Composable
fun WeatherNavGraph(
    navController: NavHostController,
) {
    
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ){

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(
                navigateToWeatherDetails = {
                    navController.navigate(WeatherDetailsScreen.route) {
                        popUpTo(Screens.SplashScreen.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Screens.WeatherDetailsScreen.route
        ) {
            WeatherDetailsScreen(
                navigateToSearch = {
                    navController.navigate(Screens.SearchCityScreen.route)
                }
            )
        }

        composable(
            route = Screens.SearchCityScreen.route
        ) {
            SearchCityScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}