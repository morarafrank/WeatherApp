package com.morarafrank.weatherapp.ui.screens.composables

import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morarafrank.weatherapp.R
import com.morarafrank.weatherapp.ui.theme.WeatherAppTheme


@Composable
fun CityWeatherUi(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {

        Text(
            text = "Nairobi",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Cloudy",
            style = MaterialTheme.typography.labelMedium.copy(
                fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
                fontSize = 20.sp
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
        ) {
            Image(
                painter = androidx.compose.ui.res.painterResource(id = R.drawable._02d),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Text(
                "21°C" ,
//                    "$temp°C" ,
                style = MaterialTheme.typography.labelMedium
            )
        }

        Text(
            "Light Rain.",
//                    "$temp°C" ,
            style = MaterialTheme.typography.labelMedium.copy(
                fontFamily = FontFamily(Font(R.font.dm_sans_regular)),
                fontSize = 16.sp
            )
        )

        Text(
            "• Feels like 20°C • Humidity: 60% • Wind: 5 km/h",
//                    "$temp°C" ,
            style = MaterialTheme.typography.bodyMedium
        )


    }
}
//
//@Preview(showBackground = true)
//@Composable
//private fun PrevCityWeatherUi() {
//    WeatherAppTheme {
//        CityWeatherUi()
//    }
//}