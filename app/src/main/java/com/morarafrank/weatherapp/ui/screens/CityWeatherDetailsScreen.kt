package com.morarafrank.weatherapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.morarafrank.weatherapp.R
import kotlin.time.Duration.Companion.days

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityWeatherDetailsScreen(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "City Weather Details",
                    )
                },
            )
        },
        content = {
            Column(
                modifier = modifier
                    .padding(it)
            ){

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                }
            }
        },
        bottomBar = {}
    )
}

//@Preview
@Composable
private fun PrevCityWeatherDetailsScreen() {
    CityWeatherDetailsScreen()
}

@Composable
fun HomeScreen(
    city: String = "Nairobi",
    temp: Int = 26,
    feelsLike: Int = 27,
    humidity: Int = 60,
    lastUpdated: String = "Today, 3:20 PM",
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAF6FF))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Weather",
                fontFamily = FontFamily(Font(R.font.dm_sans_regular)),
            )

            IconButton(onClick = onSearchClick) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = city)

        Spacer(modifier = Modifier.height(16.dp))

        Icon(
            Icons.Default.Star,
            contentDescription = "Weather Icon",
            tint = Color(0xFFFFC107),
            modifier = Modifier.size(64.dp)
        )

        Text(text = "$temp°C")
        Text(text = "Sunny • Feels like $feelsLike°C •")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = " Humidity: $humidity%")

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Last updated: $lastUpdated",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        val days: List<String> = listOf("Sun","Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        val temps: List<Int> = listOf(26, 24, 25, 22, 27, 22, 25)


        LazyRow(
            modifier = modifier.fillMaxWidth()
        ) {
            items(days){
                ForecastCard(
                    day = it,
                    temp = temps[days.indexOf(it)]
                )
            }
        }

    }
}

//@Preview
@Composable
private fun PrevHome() {
    HomeScreen(
        onSearchClick = {}
    )
}

@Composable
fun ForecastCard(day: String, temp: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(4.dp)
            .size(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
//            contentColor = Color.Black
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(day)
            Spacer(Modifier.height(4.dp))
            Icon(
                Icons.Default.Star,
                contentDescription = null,
                tint = Color.Yellow
            )
            Spacer(Modifier.height(4.dp))
            Text("$temp°C")
        }
    }
}

