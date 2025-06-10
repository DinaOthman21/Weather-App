package com.example.myweather.data.mappers

import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myweather.data.model.CurrentDTO
import com.example.myweather.data.model.CurrentUnitsDTO
import com.example.myweather.data.model.DailyDTO
import com.example.myweather.data.model.DailyUnitsDTO
import com.example.myweather.data.model.HourlyDTO
import com.example.myweather.data.model.HourlyUnitsDTO
import com.example.myweather.data.model.WeatherDTO
import com.example.myweather.domain.model.entity.location.CurrentLocation
import com.example.myweather.domain.model.entity.weather.CurrentWeather
import com.example.myweather.domain.model.entity.weather.CurrentWeatherUnit
import com.example.myweather.domain.model.entity.weather.Daily
import com.example.myweather.domain.model.entity.weather.DailyUnits
import com.example.myweather.domain.model.entity.weather.Hourly
import com.example.myweather.domain.model.entity.weather.HourlyUnite
import com.example.myweather.domain.model.entity.weather.WeatherCondition
import com.example.myweather.domain.model.entity.weather.WeatherData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

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


fun CurrentDTO.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        time = this.time,
        temperature = this.temperature_2m,
        apparentTemperature = this.apparent_temperature,
        windSpeed = this.wind_speed_10m,
        pressure = this.pressure_msl,
        humidity = this.relative_humidity_2m,
        uvIndex = this.uv_index,
        isDay = this.is_day == 1,
        weatherCode = codeToCondition(this.weather_code),
        interval = this.interval,
        precipitationProbability = this.precipitation_probability,
        rain = this.rain
        )
}

fun CurrentUnitsDTO.toCurrentWeatherUnits(): CurrentWeatherUnit {
    return CurrentWeatherUnit(
        temperature = this.temperature_2m,
        apparentTemperature = this.apparent_temperature,
        windSpeed = this.wind_speed_10m,
        pressure = this.pressure_msl,
        humidity = this.relative_humidity_2m,
        uvIndex = this.uv_index,
        isDay = this.is_day,
        weatherCode = this.weather_code,
        time = this.time,
        interval = this.interval,
        precipitationProbability = this.precipitation_probability,
        rain = this.rain
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun DailyDTO.toDaily(): List<Daily> {
    return time.indices.map { index ->
        Daily(
            max_temperature = temperature_2m_max[index],
            min_temperature = temperature_2m_min[index],
            date = dateToDay(time[index]),
            weather_code = codeToCondition(weather_code[index])
        )
    }
}

fun DailyUnitsDTO.toDailyUnits(): DailyUnits {
    return DailyUnits(
        time = this.time,
        temperature_2m_max = this.temperature_2m_max,
        temperature_2m_min = this.temperature_2m_min,
        weather_code = this.weather_code
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun HourlyDTO.toHourly(): List<Hourly> {
    return time.indices.map { index ->
        Hourly(
          date = time[index],
          temperature_2m = temperature_2m[index],
          weathercode = codeToCondition(weathercode[index])
        )
    }
}


fun HourlyUnitsDTO.toHourlyUnits(): HourlyUnite {
    return HourlyUnite(
        time = this.time,
        temperature_2m = this.temperature_2m,
        weathercode = this.weathercode
    )
}


@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDTO.toWeatherData(): WeatherData {
    return WeatherData(
        timeZone = this.timezone,
        currentWeather = this.current.toCurrentWeather(),
        currentWeatherUnit = this.current_units.toCurrentWeatherUnits(),
        daily = this.daily.toDaily(),
        dailyUnits = this.daily_units.toDailyUnits(),
        hourly = this.hourly.toHourly(),
        hourlyUnits = this.hourly_units.toHourlyUnits()
    )
}


@RequiresApi(Build.VERSION_CODES.O)
fun dateToDay(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(dateString, formatter)
    return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
}