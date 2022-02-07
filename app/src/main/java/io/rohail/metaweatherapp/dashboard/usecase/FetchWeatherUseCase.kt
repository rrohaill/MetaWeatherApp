package io.rohail.metaweatherapp.dashboard.usecase

import io.rohail.metaweatherapp.dashboard.data.WeatherRepository
import javax.inject.Inject

interface FetchWeatherUseCase {
    suspend operator fun invoke(locationName:String)
}

class FetchWeatherUseCaseImpl @Inject constructor(private val weatherRepository: WeatherRepository) :
    FetchWeatherUseCase {

    override suspend fun invoke(locationName:String) {
        weatherRepository.fetchWeather(locationName = locationName)
    }

}