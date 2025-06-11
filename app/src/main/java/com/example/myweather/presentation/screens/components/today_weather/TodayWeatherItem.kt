package com.example.myweather.presentation.screens.components.today_weather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.domain.model.entity.weather.getWeatherIcon
import com.example.myweather.presentation.WeatherUiState
import com.example.myweather.ui.theme.Urbanist
import com.example.myweather.ui.theme.currentWeatherCardBgColorForDay
import com.example.myweather.ui.theme.currentWeatherCardLabelColorForDay
import com.example.myweather.ui.theme.currentWeatherCardStrokeColorForDay
import com.example.myweather.ui.theme.currentWeatherCardValueColorForDay
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime


data class HourlyWeatherItemData(
    val icon: Painter,
    val temp: String,
    val hour: String,
)


@Composable
fun HourlyWeatherCard(
    state: WeatherUiState,
    todayWeatherItemData: HourlyWeatherItemData,
    modifier: Modifier = Modifier
) {
    val isDay = state.weatherData?.currentWeather?.isDay ?: false
    Box(
        modifier = modifier
            .height(132.dp)
            .width(88.dp)
    ) {
        Card(
            modifier = modifier
                .height(120.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .border(
                    width = 1.dp,
                    color = currentWeatherCardStrokeColorForDay(isDay),
                    shape = RoundedCornerShape(20.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = currentWeatherCardBgColorForDay(isDay)
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(bottom = 16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = todayWeatherItemData.temp,
                    fontSize = 16.sp,
                    color = currentWeatherCardValueColorForDay(isDay),
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    letterSpacing = 0.25.sp,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = todayWeatherItemData.hour,
                    fontSize = 16.sp,
                    color = currentWeatherCardLabelColorForDay(isDay),
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    letterSpacing = 0.25.sp,
                )
            }
        }

        Image(
            painter = todayWeatherItemData.icon,
            contentDescription = "",
            modifier = Modifier
                .height(58.dp)
                .width(64.dp)
                .align(Alignment.TopCenter)
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherSection(
    state: WeatherUiState,
    modifier: Modifier = Modifier
) {
    val hourlyData = state.weatherData?.hourly?.take(24) ?: emptyList()

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(hourlyData.size) { index ->
            val hourly = hourlyData[index]

            val itemData = HourlyWeatherItemData(
                icon = getWeatherIcon(hourly.weathercode, state.weatherData!!.currentWeather.isDay),
                temp = "${hourly.temperature_2m}°C",
                hour = formatToHourDotMinute(hourly.date)
            )
            HourlyWeatherCard(
                state = state,
                todayWeatherItemData = itemData,
                modifier = Modifier.width(80.dp)
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun formatToHourDotMinute(time: String): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        val localDateTime = LocalDateTime.parse(time, formatter)
        localDateTime.format(DateTimeFormatter.ofPattern("HH.mm"))
    } catch (e: Exception) {
        time
    }
}