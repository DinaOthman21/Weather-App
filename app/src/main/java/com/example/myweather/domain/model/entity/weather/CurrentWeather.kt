package com.example.myweather.domain.model.entity.weather

data class CurrentWeather(
    val time: String,
    val temperature: Double,
    val apparentTemperature: Double,
    val windSpeed: Double,
    val pressure: Double,
    val humidity: Int,
    val uvIndex: Double,
    val isDay: Boolean,
    val weatherCode: WeatherCondition
)