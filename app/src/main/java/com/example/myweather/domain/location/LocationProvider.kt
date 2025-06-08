package com.example.myweather.domain.location

import android.location.Location


interface LocationProvider {
    suspend fun getCurrentLocation(): Location?
}