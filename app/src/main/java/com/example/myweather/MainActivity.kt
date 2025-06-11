package com.example.myweather

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.myweather.presentation.RequestLocationPermission
import com.example.myweather.presentation.screens.WeatherScreen
import com.example.myweather.ui.theme.MyWeatherTheme
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyWeatherTheme {
                RequestLocationPermission {
                    WeatherScreen(get())
                }
            }
        }
    }
}




