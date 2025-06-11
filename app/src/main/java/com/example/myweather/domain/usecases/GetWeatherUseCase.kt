package com.example.myweather.domain.usecases

import com.example.myweather.domain.model.entity.weather.WeatherData
import com.example.myweather.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(): WeatherData = repository.getWeatherData()
}