package com.example.myweather.domain.model.entity.weather

data class WeatherData(
    val timeZone: String,
    val currentWeather: CurrentWeather,
    val currentWeatherUnit: CurrentWeatherUnit,
    val daily: List<Daily>,
    val dailyUnits: DailyUnits,
    val hourly: List<Hourly>,
    val hourlyUnits: HourlyUnite
)