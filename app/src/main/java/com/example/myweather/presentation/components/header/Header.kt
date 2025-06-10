package com.example.myweather.presentation.components.header


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.R
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
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        CityItem(state = weatherUiState)
        Row(modifier = Modifier.fillMaxWidth().offset(y = (-20).dp)) {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(250.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .width(220.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    Image(
                        painterResource(
                            id = R.drawable.mainly_clear
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.weight(2f))
        }
        Text(
            "${weatherUiState.weatherData?.currentWeather?.temperature}" + "°C",
            fontSize = 64.sp,
            color = tempColorForDay(weatherUiState.weatherData?.currentWeather?.is_day ?: false),
            fontFamily = Urbanist,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 64.sp,
            letterSpacing = 0.25.sp,
        )
        Text(
            text = weatherUiState.weatherData?.currentWeather?.weathercode?.description ?:"" ,
            fontSize = 16.sp,
            color = tempTextColorForDay(weatherUiState.weatherData?.currentWeather?.is_day ?: false),
            fontFamily = Urbanist,
            fontWeight = FontWeight.Medium,
            lineHeight = 16.sp,
            letterSpacing = 0.25.sp,
        )
        Spacer(Modifier.height(12.dp))
        TemperatureRow(
            state = weatherUiState
        )
    }
}