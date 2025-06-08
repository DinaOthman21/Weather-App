package com.example.myweather.domain.location

import com.example.myweather.domain.model.entity.location.CurrentLocation


interface LocationProvider {
    suspend fun getCurrentLocation(): CurrentLocation?
}