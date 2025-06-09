package com.example.myweather.domain.model.entity.weather

data class CurrentWeatherUnit(
    val interval: String,
    val is_day: String,
    val temperature: String,
    val time: String,
    val weathercode: String,
    val winddirection: String,
    val windspeed: String
)
