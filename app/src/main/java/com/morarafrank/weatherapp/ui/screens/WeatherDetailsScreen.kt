package com.morarafrank.weatherapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morarafrank.weatherapp.R
import com.morarafrank.weatherapp.ui.screens.composables.ForecastCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailsScreen(
    navigateToSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAF6FF))
            .padding(
                top = 24.dp,
                start = 8.dp,
                end = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "WeatherApp",
                fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
                fontSize = 20.sp
            )

            IconButton(onClick = navigateToSearch) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Nairobi",
            fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Icon(
            Icons.Default.Star,
            contentDescription = "Weather Icon",
            tint = Color(0xFFFFC107),
            modifier = Modifier.size(64.dp)
        )

        Text(
            text = "25°C",
            fontFamily = FontFamily(Font(R.font.dm_sans_regular)),
            fontSize = 12.sp
        )
        Text(
            text = "Sunny • Feels like $27°C •",
            fontFamily = FontFamily(Font(R.font.dm_sans_regular)),
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = " Humidity: 37%",
            fontFamily = FontFamily(Font(R.font.dm_sans_regular)),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Last updated: 10:30 AM",
            color = Color.Gray,
            fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Next 5 Days forecast:",
            fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        val days: List<String> = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
        val temps: List<Int> = listOf(26, 24, 25, 22, 27)


        FlowRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            days.forEachIndexed { index, day ->
                ForecastCard(
                    day = day,
                    temp = temps[index]
                )
            }
        }

    }
}

//@Preview
@Composable
private fun PrevHome() {
    WeatherDetailsScreen(
        navigateToSearch = {}
    )
}


