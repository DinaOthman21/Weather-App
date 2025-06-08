package com.example.myweather.data.model
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDTO(
    val interval: Int,
    val is_day: Int,
    val temperature: Double,
    val time: String,
    val weathercode: Int,
    val winddirection: Int,
    val windspeed: Double
)