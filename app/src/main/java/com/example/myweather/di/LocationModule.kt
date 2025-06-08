package com.example.myweather.di

import com.example.myweather.data.location.GoogleLocationService
import com.example.myweather.domain.location.LocationProvider
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val LocationModule = module {

    single {
        LocationServices.getFusedLocationProviderClient(androidApplication())
    }

    single<LocationProvider> {
        GoogleLocationService(
            locationClient = get(),
            context = get()
        )
    }

}