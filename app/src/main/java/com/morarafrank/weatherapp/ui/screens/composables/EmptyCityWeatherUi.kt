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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.morarafrank.weatherapp.R
import com.morarafrank.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun EmptyCityWeatherUi(modifier: Modifier = Modifier) {

}

@Composable
fun CityWeatherErrorUi(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {

        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.no_data_anim)
        )
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .size(150.dp),
            iterations = Int.MAX_VALUE,
        )

        Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )

        Button(
            onClick = onRetry,
        ) {
            Text(
                text = "Retry",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
