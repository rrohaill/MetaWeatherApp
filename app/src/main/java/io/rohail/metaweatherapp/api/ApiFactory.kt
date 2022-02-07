package io.rohail.metaweatherapp.api

import android.content.Context
import retrofit2.create

object ApiFactory {
    private lateinit var weatherApi: WeatherApi

    private const val BASE_URL = "https://www.metaweather.com/"
    private const val API_BASE_URL = BASE_URL + "api/"
    const val API_ICON_URL = BASE_URL + "static/img/weather/png/"

    fun getApi(context: Context): WeatherApi {
        if (!ApiFactory::weatherApi.isInitialized) {
            weatherApi = RetrofitFactory.retrofit(
                baseUrl = API_BASE_URL,
                context = context
            ).create()
        }
        return weatherApi
    }
}