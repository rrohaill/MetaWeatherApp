package io.rohail.metaweatherapp.dashboard.usecase

import io.rohail.metaweatherapp.dashboard.data.WeatherRepository
import io.rohail.metaweatherapp.dashboard.model.WeatherInfoResult
import io.rohail.metaweatherapp.dashboard.model.WeatherInfoUIResult
import io.rohail.metaweatherapp.utilities.toUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetWeatherUseCase {
    operator fun invoke(locationName:String): Flow<WeatherInfoUIResult>
}

class GetWeatherUseCaseImpl @Inject constructor(private val weatherRepository: WeatherRepository) :
    GetWeatherUseCase {
    override fun invoke(locationName: String): Flow<WeatherInfoUIResult> =
        weatherRepository.getWeatherResult(locationName = locationName).map { result ->
            when (result) {
                is WeatherInfoResult.Success -> if (result.data.consolidatedWeather.size > 1)
                    WeatherInfoUIResult.Success(result.data.toUI())
                else
                    WeatherInfoUIResult.Error(errorMessage = "Try again")
                is WeatherInfoResult.Error -> WeatherInfoUIResult.Error(errorMessage = result.errorMessage)
            }
        }

}