package com.example.myweather.domain.model.entity.weather

data class Hourly(
    val temperature_2m: Double,
    val date: String,
    val weathercode: WeatherCondition
)
