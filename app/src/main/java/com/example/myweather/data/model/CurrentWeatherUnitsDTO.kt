package com.example.myweather.data.model

import kotlinx.serialization.Serializable


@Serializable
data class CurrentWeatherUnitsDTO(
    val interval: String,
    val is_day: String,
    val temperature: String,
    val time: String,
    val weathercode: String,
    val winddirection: String,
    val windspeed: String
)