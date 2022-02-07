package io.rohail.metaweatherapp.dashboard.model

sealed class WeatherInfoUIResult {
    data class Success(val data: WeatherInfoUI) : WeatherInfoUIResult()
    data class Error(val errorMessage: String) : WeatherInfoUIResult()
}