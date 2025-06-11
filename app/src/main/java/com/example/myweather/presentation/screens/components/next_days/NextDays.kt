package com.example.myweather.presentation.screens.components.next_days

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.R
import com.example.myweather.domain.model.entity.weather.getWeatherIcon
import com.example.myweather.presentation.WeatherUiState
import com.example.myweather.ui.theme.Urbanist
import com.example.myweather.ui.theme.currentWeatherCardBgColorForDay
import com.example.myweather.ui.theme.currentWeatherCardValueColorForDay
import com.example.myweather.ui.theme.dayNameColorForDay
import com.example.myweather.ui.theme.tempItemBgColorForDay
import com.example.myweather.ui.theme.tempItemDividerColorForDay


data class DailyWeatherItemData(
    val day: String,
    val icon: Painter,
    val maxTemp: Int,
    val minTemp: Int,
)

@Composable
fun DailyWeatherItem(
    state : WeatherUiState,
    dailyWeatherItemData: DailyWeatherItemData,
    modifier: Modifier = Modifier
) {
    val isDay = state.weatherData!!.currentWeather.isDay
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(
                width = .3.dp,
                color = tempItemBgColorForDay(isDay),
            )
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = dailyWeatherItemData.day,
            fontSize = 16.sp,
            color = dayNameColorForDay(
                isDay
            ),
            fontFamily = Urbanist,
            fontWeight = FontWeight.Normal,
            lineHeight = 16.sp,
            letterSpacing = 0.25.sp,
            modifier = Modifier.weight(1f)
        )

        Image(
            painter = dailyWeatherItemData.icon,
            contentDescription = null,
            modifier = Modifier.height(45.dp).width(91.dp).weight(1f)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_up),
                contentDescription = null,
                modifier = Modifier.size(12.dp),
                tint = currentWeatherCardValueColorForDay(isDay)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "${dailyWeatherItemData.maxTemp}°C",
                fontSize = 16.sp,
                color = currentWeatherCardValueColorForDay(isDay),
                fontWeight = FontWeight.Medium,
                fontFamily = Urbanist,
                lineHeight = 16.sp,
                letterSpacing = 0.25.sp,
            )
            Spacer(Modifier.width(4.dp))
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(14.dp)
                    .background(tempItemDividerColorForDay(isDay))
            )
            Spacer(Modifier.width(4.dp))
            Icon(
                painter = painterResource(R.drawable.arrow_down),
                contentDescription = null,
                modifier = Modifier.size(12.dp),
                tint = currentWeatherCardValueColorForDay(isDay)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "${dailyWeatherItemData.minTemp}°C",
                fontSize = 16.sp,
                color = currentWeatherCardValueColorForDay(isDay),
                fontWeight = FontWeight.Medium,
                fontFamily = Urbanist,
                lineHeight = 16.sp,
                letterSpacing = 0.25.sp,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyWeatherData(state: WeatherUiState) {
    val dailyData = state.weatherData?.daily ?: return
    val isDay = state.weatherData.currentWeather.isDay

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Color.Black.copy(alpha = 0.2f),
                spotColor = Color.Black.copy(alpha = 0.2f),
                clip = false
            )
            .clip(RoundedCornerShape(24.dp))
            .background(currentWeatherCardBgColorForDay(isDay))
    ) {
        dailyData.drop(1).take(7).forEachIndexed { index, daily ->
            val itemData = DailyWeatherItemData(
                day = daily.date,
                icon = getWeatherIcon(daily.weather_code, isDay),
                maxTemp = daily.max_temperature.toDouble().toInt(),
                minTemp = daily.min_temperature.toDouble().toInt()
            )
            DailyWeatherItem(state = state, dailyWeatherItemData = itemData)
            if (index < dailyData.drop(1).take(7).size - 1) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = tempItemBgColorForDay(isDay))
                        .padding(horizontal = 12.dp)
                )
            }
        }
    }
}