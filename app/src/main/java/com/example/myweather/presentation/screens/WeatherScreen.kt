package com.example.myweather.presentation.screens

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.presentation.WeatherUiState
import com.example.myweather.presentation.WeatherViewModel
import com.example.myweather.presentation.screens.components.current_weather.CurrentWeatherSection
import com.example.myweather.presentation.screens.components.header.Header
import com.example.myweather.presentation.screens.components.next_days.DailyWeatherData
import com.example.myweather.presentation.screens.components.today_weather.HourlyWeatherSection
import com.example.myweather.ui.theme.Urbanist
import com.example.myweather.ui.theme.backgroundGradientForDay
import com.example.myweather.ui.theme.statusBarColor
import com.example.myweather.ui.theme.todayTextColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {

    val state by viewModel.state.collectAsState()
    val systemUiController = rememberSystemUiController()
    val isDay = state.weatherData?.currentWeather?.isDay ?: false

    LaunchedEffect(isDay) {
        isDay.let {
            systemUiController.setStatusBarColor(
                color = statusBarColor(it),
                darkIcons = it
            )
        }
    }

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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error: ${state.error}",
                    color = Color.Red,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
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
                item{
                    Spacer(Modifier.height(24.dp))
                    Text(
                        text = "Next 7 days",
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
                    DailyWeatherData(state = state)
                    Spacer(Modifier.height(32.dp))
                }
            }
        }

        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No weather data available.",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}




