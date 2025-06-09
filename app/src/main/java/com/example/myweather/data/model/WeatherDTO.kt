package com.example.myweather.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class WeatherDTO(
    val current_weather: CurrentWeatherDTO,
    val current_weather_units: CurrentWeatherUnitsDTO,
    @SerialName("daily")
    val dailyDTO: DailyDTO,
    val daily_units: DailyUnitsDTO,
    val elevation: Double,
    val generationtime_ms: Double,
    @SerialName("hourly")
    val hourlyDTO: HourlyDTO,
    val hourly_units: HourlyUnitsDTO,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)