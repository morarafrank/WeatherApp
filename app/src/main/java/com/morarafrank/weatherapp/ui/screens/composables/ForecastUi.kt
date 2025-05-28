package com.morarafrank.weatherapp.ui.screens.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.morarafrank.weatherapp.domain.model.ForecastItem

@Composable
fun ForecastUi(
    modifier: Modifier = Modifier,
    forecastData: List<ForecastItem>
) {
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