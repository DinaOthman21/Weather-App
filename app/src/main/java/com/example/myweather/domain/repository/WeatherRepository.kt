package com.example.myweather.domain.repository

import com.example.myweather.domain.model.entity.weather.WeatherData

interface WeatherRepository {
    suspend fun getWeatherData(): WeatherData
}