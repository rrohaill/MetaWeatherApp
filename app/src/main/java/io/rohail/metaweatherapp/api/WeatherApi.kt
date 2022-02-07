package io.rohail.metaweatherapp.api

import io.rohail.metaweatherapp.dashboard.model.LocationNameResult
import io.rohail.metaweatherapp.dashboard.model.WeatherInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("api/location/search/")
    suspend fun getLocationByName(
        @Query("query") locationName: String
    ): Response<List<LocationNameResult>>

    @GET("api/location/{woeid}/")
    suspend fun getWeatherInfo(
        @Path("woeid") woeid: Long
    ): Response<WeatherInfo>

}