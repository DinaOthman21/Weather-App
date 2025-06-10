package com.example.myweather.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDTO(
    val current: CurrentDTO,
    val current_units: CurrentUnitsDTO,
    val daily: DailyDTO,
    val daily_units: DailyUnitsDTO,
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: HourlyDTO,
    val hourly_units: HourlyUnitsDTO,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)