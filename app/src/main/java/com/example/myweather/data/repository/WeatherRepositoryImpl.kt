package com.example.myweather.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myweather.data.mappers.toWeatherData
import com.example.myweather.data.remote.WeatherApi
import com.example.myweather.domain.location.LocationProvider
import com.example.myweather.domain.model.entity.weather.WeatherData
import com.example.myweather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val locationProvider: LocationProvider,
    private val weatherApi: WeatherApi
) : WeatherRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(): WeatherData {
        val location = locationProvider.getCurrentLocation()
        if (location == null) {
            throw Exception("Current location could not be determined.")
        } else {
            return try {
                val weatherDto = weatherApi.getWeatherData(location.latitude, location.longitude)
                val weatherData = weatherDto.toWeatherData()
                weatherData
            } catch (e: Exception) {
                throw Exception("Failed to fetch weather data.", e)
            }
        }
    }

}