package com.example.myweather.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {

    val state by viewModel.state.collectAsState()
    ScreenContent(state)
}

@Composable
fun ScreenContent(
    state: WeatherUiState
){
    when {
        state.isLoading -> {
            Text(
                text = "Loading...",
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        state.error.isNotEmpty() -> {
            Text(
                text = "Error: ${state.error}",
                color = Color.Red,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        state.weatherData != null -> {
            val dailyList = state.weatherData.daily

            androidx.compose.foundation.lazy.LazyColumn {
                items(dailyList.size) { index ->
                    val day = dailyList[index]

                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color(0xFFBBDEFB), shape = RoundedCornerShape(12.dp))
                            .padding(16.dp)
                            .width(200.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Date: ${day.date}")
                        Text("Max Temp: ${day.max_temperature}°C", fontWeight = FontWeight.Bold)
                        Text("Min Temp: ${day.min_temperature}°C")
                        Text("Weather Code: ${day.weather_code}")
                    }
                }
            }
        }

        else -> {
            Text(
                text = "No weather data available.",
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}



