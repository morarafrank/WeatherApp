package com.morarafrank.weatherapp.ui.screens.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.morarafrank.weatherapp.R
import com.morarafrank.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun EmptyCityWeatherUi(modifier: Modifier = Modifier) {

}

@Composable
fun CityWeatherErrorUi(
    errorMessage: String = "Could not load weather data.",
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
//        Icon(
//            painter = painterResource(R.drawable.cloud_off),
//            contentDescription = null,
//            tint = MaterialTheme.colorScheme.error,
//            modifier = Modifier.size(48.dp)
//        )
        Image(
            painter = painterResource(R.drawable._02d),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )

        Button(
            onClick = onRetry,
//            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Retry",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

//@Preview(showBackground = true)
@Composable
private fun PrevCityWeatherErrorUi() {
    WeatherAppTheme {
        CityWeatherErrorUi()
    }

}
