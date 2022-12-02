package io.rohail.metaweatherapp.dashboard.data

import io.rohail.metaweatherapp.network.BaseDataSource
import io.rohail.metaweatherapp.network.ApiResult
import io.rohail.metaweatherapp.network.WeatherApi
import io.rohail.metaweatherapp.dashboard.model.LocationNameResult
import io.rohail.metaweatherapp.dashboard.data.model.WeatherInfo
import javax.inject.Inject

interface WeatherDataSource {
    suspend fun fetchLocationByName(locationName: String): ApiResult<List<LocationNameResult>>
    suspend fun fetchWeatherInfo(woeid: Long): ApiResult<WeatherInfo>
}

class WeatherDataSourceImpl @Inject constructor(private val service: WeatherApi) : BaseDataSource(),
    WeatherDataSource {

    override suspend fun fetchWeatherInfo(woeid: Long): ApiResult<WeatherInfo> = getResult {
        service.getWeatherInfo(woeid = woeid)
    }

    override suspend fun fetchLocationByName(locationName: String): ApiResult<List<LocationNameResult>> =
        getResult {
            service.getLocationByName(locationName = locationName)
        }

}