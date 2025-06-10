package com.example.myweather.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.presentation.components.header.Header
import com.example.myweather.ui.theme.backgroundGradientForDay

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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp)
                )
            }
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
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundGradientForDay(state.weatherData.currentWeather.is_day))
            ) {
                item {
                    Header(state)
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




