package com.example.myweather.domain.model.entity.weather

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.myweather.R


enum class WeatherCondition(
    val description: String
) {
    CLEAR_SKY("Clear sky"),
    MAINLY_CLEAR("Mainly clear"),
    PARTLY_CLOUDY("Partly cloudy"),
    OVERCAST("Overcast"),
    FOG("Foggy"),
    DEPOSITING_RIME_FOG("Depositing rime fog"),
    DRIZZLE_LIGHT("Drizzle: Light intensity"),
    DRIZZLE_MODERATE("Drizzle: Moderate intensity"),
    DRIZZLE_DENSE("Drizzle: Dense intensity"),
    FREEZING_DRIZZLE_LIGHT("Freezing Drizzle: Light intensity"),
    FREEZING_DRIZZLE_DENSE("Freezing Drizzle: Dense intensity"),
    RAIN_SLIGHT("Rain: Slight intensity"),
    RAIN_MODERATE("Rain: Moderate intensity"),
    RAIN_HEAVY("Rain: Heavy intensity"),
    FREEZING_RAIN_LIGHT("Freezing Rain: Light intensity"),
    FREEZING_RAIN_HEAVY("Freezing Rain: Heavy intensity"),
    SNOW_SLIGHT("Snow fall: Slight intensity"),
    SNOW_MODERATE("Snow fall: Moderate intensity"),
    SNOW_HEAVY("Snow fall: Heavy intensity"),
    SNOW_GRAINS("Snow grains"),
    RAIN_SHOWERS_SLIGHT("Rain showers: Slight intensity"),
    RAIN_SHOWERS_MODERATE("Rain showers: Moderate intensity"),
    RAIN_SHOWERS_VIOLENT("Rain showers: Violent intensity"),
    SNOW_SHOWERS_SLIGHT("Snow showers: Slight intensity"),
    SNOW_SHOWERS_HEAVY("Snow showers: Heavy intensity"),
    THUNDERSTORM_SLIGHT("Thunderstorm: Slight or moderate"),
    THUNDERSTORM_WITH_HAIL_SLIGHT("Thunderstorm with slight hail"),
    THUNDERSTORM_WITH_HAIL_HEAVY("Thunderstorm with heavy hail"),
    UNKNOWN("Unknown weather condition")
}

@Composable
fun getWeatherIcon(weatherCode: WeatherCondition, isDay: Boolean): Painter {
    return when (weatherCode) {
        WeatherCondition.CLEAR_SKY -> {
            if (isDay) painterResource(R.drawable.group_14)
            else painterResource(R.drawable.clear_sky)
        }
        WeatherCondition.MAINLY_CLEAR -> {
            if (isDay) painterResource(R.drawable.mainly_clear1)
            else painterResource(R.drawable.mainly_clear)
        }
        WeatherCondition.PARTLY_CLOUDY -> {
            if (isDay) painterResource(R.drawable.partly_cloudy)
            else painterResource(R.drawable.group_1)
        }
        WeatherCondition.OVERCAST -> {
            if (isDay) painterResource(R.drawable.group_4)
            else painterResource(R.drawable.group_4_1)
        }
        WeatherCondition.FOG -> {
            if (isDay) painterResource(R.drawable.weather_icon_1_32)
            else painterResource(R.drawable.weather_icon_1_32_1)
        }
        WeatherCondition.DEPOSITING_RIME_FOG -> {
            if (isDay) painterResource(R.drawable.weather_icon_1_32_2)
            else painterResource(R.drawable.weather_icon_1_32_2)
        }
        WeatherCondition.DRIZZLE_LIGHT -> {
            if (isDay) painterResource(R.drawable.drizzle_light)
            else painterResource(R.drawable.drizzle_light_1)
        }
        WeatherCondition.DRIZZLE_MODERATE -> {
            if (isDay) painterResource(R.drawable.drizzle_moderate)
            else painterResource(R.drawable.drizzle_moderate_1)
        }
        WeatherCondition.DRIZZLE_DENSE -> {
            if (isDay) painterResource(R.drawable.drizzle_intensity)
            else painterResource(R.drawable.drizzle_intensity_1)
        }
        WeatherCondition.FREEZING_DRIZZLE_LIGHT -> {
            if (isDay) painterResource(R.drawable.freezing_drizzle_light)
            else painterResource(R.drawable.freezing_drizzle_light)
        }
        WeatherCondition.FREEZING_DRIZZLE_DENSE -> {
            if (isDay) painterResource(R.drawable.freezing_drizzle_intensity)
            else painterResource(R.drawable.freezing_drizzle_intensity)
        }
        WeatherCondition.RAIN_SLIGHT -> {
            if (isDay) painterResource(R.drawable.weather_icon_1_58)
            else painterResource(R.drawable.rain_slight)
        }
        WeatherCondition.RAIN_MODERATE -> {
            if (isDay) painterResource(R.drawable.weather_icon_1_59)
            else painterResource(R.drawable.rain_moderate)
        }
        WeatherCondition.RAIN_HEAVY -> {
            if (isDay) painterResource(R.drawable.weather_icon_1_60)
            else painterResource(R.drawable.rain_intensity)
        }
        WeatherCondition.FREEZING_RAIN_LIGHT -> {
            if (isDay) painterResource(R.drawable.weather_icon_1_21)
            else painterResource(R.drawable.weather_icon_1_21)
        }
        WeatherCondition.FREEZING_RAIN_HEAVY -> {
            if (isDay) painterResource(R.drawable.weather_icon_1_21_1)
            else painterResource(R.drawable.weather_icon_1_21_1)
        }
        WeatherCondition.SNOW_SLIGHT -> {
            if (isDay) painterResource(R.drawable.weather_icon_1_56)
            else painterResource(R.drawable.snow_fall_light)
        }
        WeatherCondition.SNOW_MODERATE -> {
            if (isDay) painterResource(R.drawable.weather_icon_1_57)
            else painterResource(R.drawable.snow_fall_moderate)
        }
        WeatherCondition.SNOW_HEAVY -> {
            if (isDay) painterResource(R.drawable.frame_143)
            else painterResource(R.drawable.frame_143)
        }
        WeatherCondition.SNOW_GRAINS -> {
            if (isDay) painterResource(R.drawable.snow_grains)
            else painterResource(R.drawable.snow_grains_1)
        }
        WeatherCondition.RAIN_SHOWERS_SLIGHT -> {
            if (isDay) painterResource(R.drawable.weather_icon_1_60)
            else painterResource(R.drawable.rain_intensity)
        }
        WeatherCondition.RAIN_SHOWERS_MODERATE -> {
            if (isDay) painterResource(R.drawable.weather_icon_1_71)
            else painterResource(R.drawable.rain_shower_moderate_1)
        }
        WeatherCondition.RAIN_SHOWERS_VIOLENT -> {
            if (isDay) painterResource(R.drawable.weather_icon_1_72)
            else painterResource(R.drawable.rain_shower_violent)
        }
        WeatherCondition.SNOW_SHOWERS_SLIGHT -> {
            if (isDay) painterResource(R.drawable.snow_grains)
            else painterResource(R.drawable.snow_grains_1)
        }
        WeatherCondition.SNOW_SHOWERS_HEAVY -> {
            if (isDay) painterResource(R.drawable.snow_shower_heavy)
            else painterResource(R.drawable.snow_shower_heavy)
        }
        WeatherCondition.THUNDERSTORM_SLIGHT -> {
            if (isDay) painterResource(R.drawable.weather_icon_1_15)
            else painterResource(R.drawable.weather_icon_1_15)
        }
        WeatherCondition.THUNDERSTORM_WITH_HAIL_SLIGHT -> {
            if (isDay) painterResource(R.drawable.frame_142)
            else painterResource(R.drawable.frame_142)
        }
        WeatherCondition.THUNDERSTORM_WITH_HAIL_HEAVY -> {
            if (isDay) painterResource(R.drawable.thunderstrom_with_heavy_hail)
            else painterResource(R.drawable.thunderstrom_with_heavy_hail)
        }
        WeatherCondition.UNKNOWN -> {
            if (isDay) painterResource(R.drawable.thunderstrom_with_heavy_hail)
            else painterResource(R.drawable.thunderstrom_with_heavy_hail)
        }
    }
}