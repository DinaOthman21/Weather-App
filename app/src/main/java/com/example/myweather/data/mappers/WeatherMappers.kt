package com.example.myweather.data.mappers

import android.location.Location

import com.example.myweather.domain.model.entity.location.CurrentLocation

fun Location.toAppLocation(): CurrentLocation {
    return CurrentLocation(
        latitude = this.latitude,
        longitude = this.longitude
    )
}