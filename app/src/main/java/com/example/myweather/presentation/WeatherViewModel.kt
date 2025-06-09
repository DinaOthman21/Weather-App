package com.example.myweather.presentation

import androidx.lifecycle.ViewModel
import com.example.myweather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherUiState(null,false,""))
    val state = _state.asStateFlow()

    init {
        getWeather()
    }

    fun getWeather() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
                error = ""
            )
            try {
                val result = weatherRepository.getWeatherData()
                _state.value = _state.value.copy(
                    weatherData = result,
                    isLoading = false,
                    error = ""
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }

        }
    }
}