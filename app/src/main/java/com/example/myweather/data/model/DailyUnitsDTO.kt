package com.example.myweather.data.model
import kotlinx.serialization.Serializable

@Serializable
data class DailyUnitsDTO(
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val weather_code: String
)