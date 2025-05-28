package com.morarafrank.weatherapp.ui.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.morarafrank.weatherapp.domain.model.ForecastItem

@Composable
fun ForecastUi(
    modifier: Modifier = Modifier,
    forecastData: List<ForecastItem>
) {

    //        FlowRow(
//            modifier = modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly,
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            forecastData.forEach { forecastItem ->
//                ForecastCard(
//                    forecastItem
//                )
//            }
//        }
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxWidth()
            .heightIn(min = 300.dp, max = 500.dp)
    ) {

        items(forecastData){ forecastItem ->
            ForecastCard(
                forecastItem = forecastItem
            )
        }
    }
}