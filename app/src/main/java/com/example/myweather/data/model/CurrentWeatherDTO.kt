package com.example.myweather.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CurrentWeatherDTO(
    @SerialName("interval")
    val interval: Int,

    @SerialName("is_day")
    val is_day: Int,

    @SerialName("temperature")
    val temperature: Double,

    @SerialName("time")
    val time: String,

    @SerialName("weathercode")
    val weathercode: Int,

    @SerialName("winddirection")
    val winddirection: Int,

    @SerialName("windspeed")
    val windspeed: Double
)