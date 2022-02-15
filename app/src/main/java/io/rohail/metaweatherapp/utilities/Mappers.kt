package io.rohail.metaweatherapp.utilities

import io.rohail.metaweatherapp.dashboard.data.model.WeatherInfo
import io.rohail.metaweatherapp.dashboard.model.WeatherInfoUI

fun WeatherInfo.toUI(): WeatherInfoUI {
    val consolidateWeather = consolidatedWeather.elementAt(1)
    return WeatherInfoUI(
        title = title,
        sunRise = sunRise,
        sunSet = sunSet,
        createdDate = consolidateWeather.created,
        temperature = consolidateWeather.theTemp,
        minTemp = consolidateWeather.minTemp,
        maxTemp = consolidateWeather.maxTemp,
        weatherStateAbbr = consolidateWeather.weatherStateAbbr,
        weatherStateName = consolidateWeather.weatherStateName,
        humidity = consolidateWeather.humidity,
        windSpeed = consolidateWeather.windSpeed,
        airPressure = consolidateWeather.airPressure
    )
}