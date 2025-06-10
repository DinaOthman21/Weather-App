package com.example.myweather.presentation.components.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.R
import com.example.myweather.presentation.WeatherUiState
import com.example.myweather.ui.theme.Urbanist
import com.example.myweather.ui.theme.locationColorForDay

@Composable
fun CityItem(
    state : WeatherUiState
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.location),
            contentDescription = "location icon",
            tint = locationColorForDay(state.weatherData?.currentWeather?.isDay ?: true)
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = state.weatherData!!.timeZone.split("/")[1],
            fontSize = 16.sp,
            color = locationColorForDay(state.weatherData.currentWeather.isDay),
            fontWeight = FontWeight.Medium,
            fontFamily = Urbanist,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        )
    }

}