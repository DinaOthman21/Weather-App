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
        45 -> WeatherCondition.FOG
        48 -> WeatherCondition.DEPOSITING_RIME_FOG
        51 -> WeatherCondition.DRIZZLE_LIGHT
        53 -> WeatherCondition.DRIZZLE_MODERATE
        55 -> WeatherCondition.DRIZZLE_DENSE
        56 -> WeatherCondition.FREEZING_DRIZZLE_LIGHT
        57 -> WeatherCondition.FREEZING_DRIZZLE_DENSE
        61 -> WeatherCondition.RAIN_SLIGHT
        63 -> WeatherCondition.RAIN_MODERATE
        65 -> WeatherCondition.RAIN_HEAVY
        66 -> WeatherCondition.FREEZING_RAIN_LIGHT
        67 -> WeatherCondition.FREEZING_RAIN_HEAVY
        71 -> WeatherCondition.SNOW_SLIGHT
        73 -> WeatherCondition.SNOW_MODERATE
        75 -> WeatherCondition.SNOW_HEAVY
        77 -> WeatherCondition.SNOW_GRAINS
        80 -> WeatherCondition.RAIN_SHOWERS_SLIGHT
        81 -> WeatherCondition.RAIN_SHOWERS_MODERATE
        82 -> WeatherCondition.RAIN_SHOWERS_VIOLENT
        85 -> WeatherCondition.SNOW_SHOWERS_SLIGHT
        86 -> WeatherCondition.SNOW_SHOWERS_HEAVY
        95 -> WeatherCondition.THUNDERSTORM_SLIGHT
        96 -> WeatherCondition.THUNDERSTORM_WITH_HAIL_SLIGHT
        99 -> WeatherCondition.THUNDERSTORM_WITH_HAIL_HEAVY
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