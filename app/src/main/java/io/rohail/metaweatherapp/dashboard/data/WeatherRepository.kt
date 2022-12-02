package io.rohail.metaweatherapp.dashboard.data

import io.rohail.metaweatherapp.network.ApiResult
import io.rohail.metaweatherapp.dashboard.model.WeatherInfoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface WeatherRepository {

    suspend fun fetchWeather(locationName: String)

    fun getWeatherResult(locationName: String): Flow<WeatherInfoResult>
}

class WeatherRepositoryImpl @Inject constructor(private val weatherDataSource: WeatherDataSource) :
    WeatherRepository {

    private val weatherResult = hashMapOf<String, MutableSharedFlow<WeatherInfoResult>>()

    override suspend fun fetchWeather(locationName: String) {
        weatherDataSource.fetchLocationByName(locationName = locationName).let { result ->
            when (result) {
                is ApiResult.Success -> if (result.data.isNotEmpty())
                    withContext(Dispatchers.IO) {
                        fetchWeatherInfo(
                            locationName = locationName,
                            woeid = result.data.first().woeid
                        )
                    }
                else
                    getWeatherResultInternal(locationName = locationName).emit(
                        WeatherInfoResult.Error(errorMessage = "Try again")
                    )
                is ApiResult.Error -> getWeatherResultInternal(locationName = locationName).emit(
                    WeatherInfoResult.Error(errorMessage = result.message)
                )
            }
        }
    }

    private suspend fun fetchWeatherInfo(locationName: String, woeid: Long) {
        weatherDataSource.fetchWeatherInfo(woeid = woeid).let { result ->
            when (result) {
                is ApiResult.Success -> getWeatherResultInternal(locationName = locationName).emit(
                    WeatherInfoResult.Success(data = result.data)
                )
                is ApiResult.Error -> getWeatherResultInternal(locationName = locationName).emit(
                    WeatherInfoResult.Error(errorMessage = result.message)
                )
            }
        }
    }

    override fun getWeatherResult(locationName: String): Flow<WeatherInfoResult> =
        getWeatherResultInternal(locationName = locationName)

    private fun getWeatherResultInternal(locationName: String): MutableSharedFlow<WeatherInfoResult> =
        weatherResult.getOrPut(locationName) { MutableSharedFlow() }

}