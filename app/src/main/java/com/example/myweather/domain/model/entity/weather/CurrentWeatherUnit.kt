package com.example.myweather.domain.model.entity.weather

data class CurrentWeatherUnit(
    val temperature: String,
    val apparentTemperature: String,
    val windSpeed: String,
    val pressure: String,
    val humidity: String,
    val uvIndex: String,
    val isDay: String,
    val weatherCode: String,
    val time: String
)
