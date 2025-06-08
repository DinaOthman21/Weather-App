package com.example.myweather.data.model

import kotlinx.serialization.Serializable


@Serializable
data class WeatherDTO(
    val current_weather: CurrentWeatherDTO,
    val current_weather_units: CurrentWeatherUnitsDTO,
    val elevation: Double,
    val generationtime_ms: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)