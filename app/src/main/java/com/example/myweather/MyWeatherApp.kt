package com.example.myweather

import android.app.Application
import com.example.myweather.di.LocationModule
import com.example.myweather.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyWeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyWeatherApp)
            modules(appModule,LocationModule)
        }
    }
}