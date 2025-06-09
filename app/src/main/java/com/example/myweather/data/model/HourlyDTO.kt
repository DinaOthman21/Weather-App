package com.example.myweather.data.model
import kotlinx.serialization.Serializable

@Serializable
data class HourlyDTO(
    val temperature_2m: List<Double>,
    val time: List<String>,
    val weathercode: List<Int>
)