package com.example.myweather.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val locationColor = Color(0xFF323232)
val tempColor = Color(0xFF060414)
val white = Color(0xFFFFFFFF)
val SkyBlueLight = Color(0xFF87CEFA)
val gradientColors = listOf(
    SkyBlueLight,
    white
)

val DarkPurpleBlueStart = Color(0xFF060414)
val DarkPurpleBlueEnd = Color(0xFF0D0C19)
val gradientColorsDark = listOf(
    DarkPurpleBlueStart,
    DarkPurpleBlueEnd
)
val tempItemColor = Color(0xFF060414)

fun backgroundGradientForDay(isDay: Boolean): Brush {
    return if (isDay) {
        Brush.verticalGradient(colors = gradientColors)
    } else {
        Brush.verticalGradient(
            colors = gradientColorsDark ,
            startY = 0f,
            endY = Float.POSITIVE_INFINITY
        )
    }
}

fun locationColorForDay(isDay: Boolean): Color {
    return if (isDay) locationColor else Color.White
}

fun tempColorForDay(isDay: Boolean): Color {
    return if (isDay) tempColor else Color.White
}

fun tempTextColorForDay(isDay: Boolean): Color {
    return if (isDay) Color(0x99060414) else Color.White.copy(alpha = .6f)
}

fun tempItemDividerColorForDay(isDay: Boolean): Color {
    return if (isDay) tempItemColor.copy(.24f) else Color.White.copy(.24f)
}

fun tempItemBgColorForDay(isDay: Boolean): Color {
    return if (isDay) tempItemColor.copy(alpha = .08f) else Color.White.copy(.08f)
}

fun tempItemIconColorForDay(isDay: Boolean): Color {
    return if (isDay) tempItemColor.copy(alpha = .6f) else Color.White.copy(.87f)
}

fun currentWeatherCardBgColorForDay(isDay: Boolean): Color {
    return if (isDay) Color.White.copy(.7f) else tempColor.copy(.7f)
}

fun currentWeatherCardValueColorForDay(isDay: Boolean): Color {
    return if (isDay) DarkPurpleBlueStart.copy(.87f) else Color.White.copy(.87f)
}

fun currentWeatherCardLabelColorForDay(isDay: Boolean): Color {
    return if (isDay) DarkPurpleBlueStart.copy(.6f) else Color.White.copy(.6f)
}

fun currentWeatherCardStrokeColorForDay(isDay: Boolean): Color {
    return if (isDay) tempItemColor.copy(.08f) else Color.White.copy(.08f)
}

