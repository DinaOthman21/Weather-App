package com.example.myweather.presentation.components.header

import androidx.compose.material3.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.R
import com.example.myweather.ui.theme.Urbanist
import com.example.myweather.ui.theme.tempItemColor

@Composable
fun TemperatureRow(
    modifier: Modifier = Modifier,
    maxTemp: Int,
    minTemp: Int
) {

    Row(
        modifier = modifier
            .height(35.dp)
            .clip(CircleShape)
            .background(tempItemColor.copy(alpha = .08f))
            .padding(horizontal = 24.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TemperatureItem(
            icon = painterResource(id = R.drawable.arrow_up),
            temperature = maxTemp,
        )
        Spacer(Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(24.dp)
                .background(tempItemColor.copy(alpha = .24f))
        )
        Spacer(Modifier.width(8.dp))
        TemperatureItem(
            icon = painterResource(id = R.drawable.arrow_down),
            temperature = minTemp,
        )
    }
}

@Composable
private fun TemperatureItem(
    icon: Painter,
    temperature: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = tempItemColor.copy(alpha = .6f)
        )
        Text(
            text = "$temperature°C",
            fontSize = 16.sp,
            color = tempItemColor.copy(alpha = .6f),
            fontWeight = FontWeight.Medium,
            fontFamily = Urbanist,
            lineHeight = 16.sp,
            letterSpacing = 0.25.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TemperatureDisplayPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD2E7F8)),
        contentAlignment = Alignment.Center
    ) {
        TemperatureRow(
           maxTemp =  53,
           minTemp =  30
        )
    }
}