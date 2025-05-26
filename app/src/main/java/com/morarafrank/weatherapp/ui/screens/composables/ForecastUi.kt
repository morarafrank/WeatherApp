package com.morarafrank.weatherapp.ui.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//@Preview
//    (showBackground = true)
@Composable
fun ForecastUi(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "5-Day Forecast: ",
                style = MaterialTheme.typography.bodyLarge,
            )

            FlowRow(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                repeat(5) {
                    ForecastCard(
                        main = "Cloudy",
                        temp = 25,
                        details = "chance of rain"
                    )
                }
            }
        }

        Text(
            text = "Last updated: 5 mins ago",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )


    }
}