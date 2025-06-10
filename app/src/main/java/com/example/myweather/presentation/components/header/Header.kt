package com.example.myweather.presentation.components.header


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.domain.model.entity.weather.WeatherCondition
import com.example.myweather.domain.model.entity.weather.getWeatherIcon
import com.example.myweather.presentation.WeatherUiState
import com.example.myweather.ui.theme.Urbanist
import com.example.myweather.ui.theme.tempColorForDay
import com.example.myweather.ui.theme.tempTextColorForDay



@Composable
fun Header(
    weatherUiState: WeatherUiState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        CityItem(state = weatherUiState)
        Spacer(modifier = Modifier.height(12.dp))
            Image(
                 getWeatherIcon(
                    weatherCode = weatherUiState.weatherData?.currentWeather?.weatherCode ?: WeatherCondition.UNKNOWN,
                    isDay = weatherUiState.weatherData?.currentWeather?.isDay ?: false,
                ),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth().padding(start = 67.dp, end = 73.dp)
            )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "${weatherUiState.weatherData?.currentWeather?.temperature}" + "°C",
            fontSize = 64.sp,
            color = tempColorForDay(weatherUiState.weatherData?.currentWeather?.isDay ?: false),
            fontFamily = Urbanist,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 64.sp,
            letterSpacing = 0.25.sp,
        )
        Text(
            text = weatherUiState.weatherData?.currentWeather?.weatherCode?.description ?: "",
            fontSize = 16.sp,
            color = tempTextColorForDay(weatherUiState.weatherData?.currentWeather?.isDay ?: false),
            fontFamily = Urbanist,
            fontWeight = FontWeight.Medium,
            lineHeight = 16.sp,
            letterSpacing = 0.25.sp,
        )
        Spacer(Modifier.height(12.dp))
        TemperatureRow(state = weatherUiState)
    }
}