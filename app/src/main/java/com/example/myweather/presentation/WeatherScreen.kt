package com.example.myweather.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.presentation.components.current_weather.CurrentWeatherSection
import com.example.myweather.presentation.components.header.Header
import com.example.myweather.presentation.components.today_weather.HourlyWeatherSection
import com.example.myweather.ui.theme.Urbanist
import com.example.myweather.ui.theme.backgroundGradientForDay
import com.example.myweather.ui.theme.todayTextColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {

    val state by viewModel.state.collectAsState()
    ScreenContent(state)
}

@RequiresApi(Build.VERSION_CODES.O)
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
                    .safeDrawingPadding()
                    .background(backgroundGradientForDay(state.weatherData.currentWeather.isDay))
            ) {
                item {
                    Header(state)
                    Spacer(Modifier.height(24.dp))
                }
                item {
                    CurrentWeatherSection(
                        state = state,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                item{
                    Spacer(Modifier.height(24.dp))
                    Text(
                        text = "Today",
                        fontSize = 20.sp,
                        color = todayTextColor(
                            state.weatherData.currentWeather.isDay
                        ),
                        fontFamily = Urbanist,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 20.sp,
                        letterSpacing = 0.25.sp,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                    HourlyWeatherSection(
                        state = state,
                    )

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




