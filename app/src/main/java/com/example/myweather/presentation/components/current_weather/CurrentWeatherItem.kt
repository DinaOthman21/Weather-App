package com.example.myweather.presentation.components.current_weather

import androidx.compose.foundation.border
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.R
import com.example.myweather.presentation.WeatherUiState
import com.example.myweather.ui.theme.Urbanist
import com.example.myweather.ui.theme.currentWeatherCardBgColorForDay
import com.example.myweather.ui.theme.currentWeatherCardLabelColorForDay
import com.example.myweather.ui.theme.currentWeatherCardStrokeColorForDay
import com.example.myweather.ui.theme.currentWeatherCardValueColorForDay

data class CurrentWeatherItemData(
    val icon: Painter,
    val value: String,
    val label: String,
)

@Composable
fun CurrentWeatherCard(
    state : WeatherUiState,
    currentWeatherItemData : CurrentWeatherItemData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(115.dp)
            .border(
                width = 1.dp,
                color = currentWeatherCardStrokeColorForDay(state.weatherData?.currentWeather?.isDay ?: false),
                shape = RoundedCornerShape(24.dp)
            ),
         colors = CardDefaults.cardColors(
             containerColor = currentWeatherCardBgColorForDay(state.weatherData?.currentWeather?.isDay ?: false)
         ),
         shape = RoundedCornerShape(24.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = currentWeatherItemData.icon,
                contentDescription = currentWeatherItemData.label,
                tint = Color(0xFF87CEFA),
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = currentWeatherItemData.value,
                fontSize = 20.sp,
                color = currentWeatherCardValueColorForDay(state.weatherData?.currentWeather?.isDay ?: false),
                fontFamily = Urbanist,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = currentWeatherItemData.label,
                fontSize = 14.sp,
                color = currentWeatherCardLabelColorForDay(state.weatherData?.currentWeather?.isDay ?: false),
                fontFamily = Urbanist,
                fontWeight = FontWeight.Normal,
                lineHeight = 14.sp,
                letterSpacing = 0.25.sp,
            )
        }
    }
}

@Composable
fun createCurrentWeatherCardData(weatherUiState: WeatherUiState): List<CurrentWeatherItemData> {
    val currentWeather = weatherUiState.weatherData?.currentWeather
    val currentWeatherUnit = weatherUiState.weatherData?.currentWeatherUnit


    val windIcon = painterResource(id = R.drawable.fast_wind)
    val humidityIcon = painterResource(id = R.drawable.humidity)
    val rainIcon = painterResource(id = R.drawable.rain)
    val uvIndexIcon = painterResource(id = R.drawable.uv_02)
    val pressureIcon = painterResource(id = R.drawable.arrow_down_05)
    val feelsLikeIcon = painterResource(id = R.drawable.temperature)

    return listOf(
        CurrentWeatherItemData(
            icon = windIcon,
            value = "${currentWeather?.windSpeed?.toInt()} ${currentWeatherUnit?.windSpeed}",
            label = "Wind"
        ),
        CurrentWeatherItemData(
            icon = humidityIcon,
            value = "${currentWeather?.humidity}${currentWeatherUnit?.humidity}",
            label = "Humidity"
        ),
        CurrentWeatherItemData(
            icon = rainIcon,
            value = "${currentWeather?.rain?.toInt()}%",
            label = "Rain"
        ),
        CurrentWeatherItemData(
            icon = uvIndexIcon,
            value = "${currentWeather?.uvIndex?.toInt()}${currentWeatherUnit?.uvIndex}",
            label = "UV Index"
        ),
        CurrentWeatherItemData(
            icon = pressureIcon,
            value = "${currentWeather?.pressure?.toInt()} ${currentWeatherUnit?.pressure}",
            label = "Pressure"
        ),
        CurrentWeatherItemData(
            icon = feelsLikeIcon,
            value = "${currentWeather?.temperature?.toInt()}${currentWeatherUnit?.temperature}",
            label = "Feels like"
        )
    )
}


@Composable
fun CurrentWeatherSection(
    state: WeatherUiState,
    modifier: Modifier = Modifier
) {
    val detailItems = createCurrentWeatherCardData(state)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CurrentWeatherCard(
                state = state,
                currentWeatherItemData = detailItems[0],
                modifier = Modifier.weight(1f)
            )
            CurrentWeatherCard(
                state = state,
                currentWeatherItemData = detailItems[1],
                modifier = Modifier.weight(1f)
            )
            CurrentWeatherCard(
                state = state,
                currentWeatherItemData = detailItems[2],
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CurrentWeatherCard(
                state = state,
                currentWeatherItemData = detailItems[3],
                modifier = Modifier.weight(1f)
            )
            CurrentWeatherCard(
                state = state,
                currentWeatherItemData = detailItems[4],
                modifier = Modifier.weight(1f)
            )
            CurrentWeatherCard(
                state = state,
                currentWeatherItemData = detailItems[5],
                modifier = Modifier.weight(1f)
            )
        }
    }
}

