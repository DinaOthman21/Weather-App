package com.example.myweather.domain.model.entity.weather

data class DailyUnits(
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val weather_code: String
)
