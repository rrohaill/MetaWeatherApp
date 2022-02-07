package io.rohail.metaweatherapp.dashboard.model

sealed class WeatherInfoResult {
    data class Success(val data: WeatherInfo) : WeatherInfoResult()
    data class Error(val errorMessage: String) : WeatherInfoResult()
}
