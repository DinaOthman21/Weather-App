package com.example.myweather.domain.model.entity.weather

data class Daily(
    val max_temperature: Double,
    val min_temperature: Double,
    val date: String,
    val weather_code: WeatherCondition
)
