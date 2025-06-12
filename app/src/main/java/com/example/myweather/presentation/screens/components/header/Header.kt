
package com.example.myweather.presentation.screens.components.header


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Header(
    weatherUiState: WeatherUiState,
    scrollOffset : Float
) {
    val isDay =weatherUiState.weatherData?.currentWeather?.isDay ?: false
    val currentWeather = weatherUiState.weatherData?.currentWeather
    val compactLayout = scrollOffset > 0.5f

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        CityItem(state = weatherUiState)
        Spacer(modifier = Modifier.height(12.dp))
        AnimatedContent(
            targetState = compactLayout,
            transitionSpec = { fadeIn() with fadeOut() },
            label = "HeaderLayoutTransition"
        ) { isCompact ->
            if (isCompact) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(44.dp)
                ) {
                    Image(
                        getWeatherIcon(
                            weatherCode = currentWeather?.weatherCode ?: WeatherCondition.UNKNOWN,
                            isDay = isDay,
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .weight(1f)
                            .height(112.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            "${currentWeather?.temperature}${weatherUiState.weatherData?.currentWeatherUnit?.temperature ?: ""}",
                            fontSize = 32.sp,
                            color = tempColorForDay(isDay),
                            fontFamily = Urbanist,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 32.sp,
                            letterSpacing = 0.25.sp,
                        )
                        Text(
                            text = currentWeather?.weatherCode?.description ?: "",
                            fontSize = 14.sp,
                            color = tempTextColorForDay(isDay),
                            fontFamily = Urbanist,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 14.sp,
                            letterSpacing = 0.25.sp,
                        )
                        Spacer(Modifier.height(12.dp))
                        TemperatureRow(state = weatherUiState)
                    }
                }
            }
            else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        getWeatherIcon(
                            weatherCode = currentWeather?.weatherCode ?: WeatherCondition.UNKNOWN,
                            isDay = isDay,
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 67.dp, end = 73.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "${currentWeather?.temperature}${weatherUiState.weatherData?.currentWeatherUnit?.temperature ?: ""}",
                        fontSize = 64.sp,
                        color = tempColorForDay(isDay),
                        fontFamily = Urbanist,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 64.sp,
                        letterSpacing = 0.25.sp,
                    )
                    Text(
                        text = currentWeather?.weatherCode?.description ?: "",
                        fontSize = 16.sp,
                        color = tempTextColorForDay(isDay),
                        fontFamily = Urbanist,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 16.sp,
                        letterSpacing = 0.25.sp,
                    )
                    Spacer(Modifier.height(12.dp))
                    TemperatureRow(state = weatherUiState)
                }
            }
        }
    }
}

