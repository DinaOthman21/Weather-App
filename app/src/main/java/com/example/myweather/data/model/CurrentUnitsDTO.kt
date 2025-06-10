package com.example.myweather.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrentUnitsDTO(
    val apparent_temperature: String,
    val interval: String,
    val is_day: String,
    val precipitation_probability: String,
    val pressure_msl: String,
    val rain: String,
    val relative_humidity_2m: String,
    val temperature_2m: String,
    val time: String,
    val uv_index: String,
    val weather_code: String,
    val wind_speed_10m: String
)