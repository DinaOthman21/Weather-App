package com.example.myweather.data.model
import kotlinx.serialization.Serializable

@Serializable
data class DailyDTO(
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val time: List<String>,
    val weather_code: List<Int>
)