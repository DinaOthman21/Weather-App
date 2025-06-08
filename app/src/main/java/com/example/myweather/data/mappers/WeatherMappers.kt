package com.example.myweather.data.mappers

import android.location.Location
import com.example.myweather.data.model.CurrentWeatherDTO
import com.example.myweather.data.model.WeatherDTO

import com.example.myweather.domain.model.entity.location.CurrentLocation
import com.example.myweather.domain.model.entity.weather.CurrentWeather
import com.example.myweather.domain.model.entity.weather.WeatherCondition
import com.example.myweather.domain.model.entity.weather.WeatherData

fun Location.toAppLocation(): CurrentLocation {
    return CurrentLocation(
        latitude = this.latitude,
        longitude = this.longitude
    )
}

private fun codeToCondition(code: Int): WeatherCondition {
    return when (code) {
        0 -> WeatherCondition.CLEAR_SKY
        1 -> WeatherCondition.MAINLY_CLEAR
        2 -> WeatherCondition.PARTLY_CLOUDY
        3 -> WeatherCondition.OVERCAST
        45, 48 -> WeatherCondition.FOG
        51, 53, 55 -> WeatherCondition.DRIZZLE
        56, 57 -> WeatherCondition.LIGHT_FREEZING_DRIZZLE
        61 -> WeatherCondition.SLIGHT_RAIN
        63 -> WeatherCondition.MODERATE_RAIN
        65 -> WeatherCondition.HEAVY_INTENSITY_RAIN
        66 -> WeatherCondition.LIGHT_FREEZING_RAIN
        67 -> WeatherCondition.HEAVY_INTENSITY_FREEZING_RAIN
        71 -> WeatherCondition.SLIGHT_SNOW_FALL
        73 -> WeatherCondition.MODERATE_SNOW_FALL
        75 -> WeatherCondition.HEAVY_INTENSITY_SNOW_FALL
        77 -> WeatherCondition.SNOW_GRAINS
        80 -> WeatherCondition.SLIGHT_RAIN_SHOWERS
        81 -> WeatherCondition.MODERATE_RAIN_SHOWERS
        82 -> WeatherCondition.VIOLENT_RAIN_SHOWERS
        85 -> WeatherCondition.SLIGHT_SNOW_SHOWERS
        86 -> WeatherCondition.HEAVY_SNOW_SHOWERS
        95 -> WeatherCondition.SLIGHT_THUNDERSTORM
        96, 99 -> WeatherCondition.SLIGHT_THUNDERSTORM_WITH_HAIL
        else -> WeatherCondition.UNKNOWN
    }
}


fun CurrentWeatherDTO.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        time = this.time,
        interval = this.interval,
        temperature = this.temperature,
        windspeed = this.windspeed,
        winddirection = this.winddirection,
        is_day = this.is_day == 1,
        weathercode = codeToCondition(this.weathercode)
    )
}


fun WeatherDTO.toWeatherData(): WeatherData {
    return WeatherData(
            timeZone = this.timezone,
        currentWeather = this.current_weather.toCurrentWeather()
    )
}