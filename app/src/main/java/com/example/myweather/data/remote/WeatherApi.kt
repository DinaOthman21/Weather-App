package com.example.myweather.data.remote

import com.example.myweather.data.model.WeatherDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*

class WeatherApi(
    private val client: HttpClient
) {
    suspend fun getWeatherData(lat: Double, long: Double): WeatherDTO {
        val url = "$OPEN_METEO_URL?latitude=$lat&longitude=$long&current_weather=true&hourly=temperature_2m&daily=weather_code,temperature_2m_max,temperature_2m_min&timezone=auto"
        return client.get(url).body()
    }

    companion object {
        private const val OPEN_METEO_URL = "https://api.open-meteo.com/v1/forecast"
    }
}


