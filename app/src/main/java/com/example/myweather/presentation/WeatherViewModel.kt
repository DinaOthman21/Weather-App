package com.example.myweather.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import com.example.myweather.domain.usecases.GetWeatherUseCase
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherUiState(null, false, ""))
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
                val result = getWeatherUseCase.invoke()
                _state.update {
                    it.copy(
                        weatherData = result,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        error = e.message ?: "Unknown error",
                        isLoading = false
                    )
                }
            }

        }
    }
}