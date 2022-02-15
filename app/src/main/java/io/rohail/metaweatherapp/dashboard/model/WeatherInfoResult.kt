package io.rohail.metaweatherapp.dashboard.model

import io.rohail.metaweatherapp.dashboard.data.model.WeatherInfo

sealed class WeatherInfoResult {
    data class Success(val data: WeatherInfo) : WeatherInfoResult()
    data class Error(val errorMessage: String) : WeatherInfoResult()
}
