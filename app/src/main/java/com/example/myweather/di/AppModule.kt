package com.example.myweather.di

import com.example.myweather.data.remote.WeatherApi
import com.example.myweather.data.repository.WeatherRepositoryImpl
import com.example.myweather.domain.repository.WeatherRepository
import com.example.myweather.presentation.WeatherViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    useAlternativeNames = false
                })
            }
        }
    }
    single { WeatherApi(get()) }
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            locationProvider = get(),
            weatherApi = get()
        )
    }
    viewModel { WeatherViewModel(get()) }
}