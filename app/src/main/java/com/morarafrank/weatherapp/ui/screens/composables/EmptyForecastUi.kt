package com.morarafrank.weatherapp.ui.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morarafrank.weatherapp.R

@Composable
fun EmptyForecastUi(
    error: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


//        val composition by rememberLottieComposition(
//            spec = LottieCompositionSpec.RawRes(R.raw.no_data_anim)
//        )
//        LottieAnimation(
//            composition = composition,
//            modifier = Modifier
//                .size(200.dp),
//            iterations = Int.MAX_VALUE,
//        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = error,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}