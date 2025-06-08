package com.example.myweather.domain.model.entity.weather

data class CurrentWeather(
    val time: String,
    val interval: Int,
    val temperature: Double,
    val windspeed: Double,
    val winddirection: Int,
    val is_day: Boolean,
    val weathercode: WeatherCondition
)