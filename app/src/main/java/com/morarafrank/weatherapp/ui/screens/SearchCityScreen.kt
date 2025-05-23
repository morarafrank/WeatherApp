package com.morarafrank.weatherapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCityScreen(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
//                        text = "Search City",
                        text = "",
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                navigationIcon = {

                    IconButton(
                        onClick = {},

                    ){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        },
        content = {

            var query by remember { mutableStateOf("") }
            val cities = listOf("Nairobi", "New York", "Tokyo", "Cairo", "Lagos")
            val filtered = cities.filter { it.contains(query, ignoreCase = true) }

            Column(
                modifier = modifier.fillMaxSize()
                    .padding(it)
            ) {

                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    label = {
                        Text("Search city", style = MaterialTheme.typography.bodyLarge)
                            },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search city",

                        )
                    },
//                    colors = TextFieldDefaults.outlinedTextFieldColors(
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent
//                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp)

                )

            }
         },
        bottomBar = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun PrevSearchScreen() {
    SearchCityScreen()
}