package com.example.myweather.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import com.example.myweather.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val Urbanist = FontFamily(
    Font(R.font.urbanist_regular , weight = FontWeight.Normal) ,
    Font(R.font.urbanist_medium , weight = FontWeight.Medium) ,
    Font(R.font.urbanist_bold , weight = FontWeight.Bold) ,
    Font(R.font.urbanist_semi_bold , weight = FontWeight.SemiBold)
)