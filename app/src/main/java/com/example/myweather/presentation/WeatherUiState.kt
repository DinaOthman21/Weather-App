package com.example.myweather.presentation

import com.example.myweather.domain.model.entity.weather.WeatherData

data class WeatherUiState(
    val weatherData: WeatherData?,
    val isLoading: Boolean,
    val error: String
)
