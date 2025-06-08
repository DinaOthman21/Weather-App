package com.example.myweather.data.model
import kotlinx.serialization.Serializable

@Serializable
data class HourlyUnitsDTO(
    val temperature_2m: String,
    val time: String
)