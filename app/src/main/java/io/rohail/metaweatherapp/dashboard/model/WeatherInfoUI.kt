package io.rohail.metaweatherapp.dashboard.model

import io.rohail.metaweatherapp.utilities.WeatherDateFormatter
import kotlin.math.roundToInt
import kotlin.math.roundToLong

data class WeatherInfoUI(
    val title: String,
    val sunRise: String,
    val sunSet: String,
    val createdDate: String,
    val temperature: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val weatherStateAbbr: String,
    val weatherStateName: String,
    val humidity: Int,
    val windSpeed: Double,
    val airPressure: Double
) {
    val formattedTemp: String
        get() = String.format("%d°", temperature.roundToInt())

    val formattedMinTemp: String
        get() = String.format("%d°", minTemp.roundToInt())

    val formattedMaxTemp: String
        get() = String.format("%d°", maxTemp.roundToInt())

    val iconUrl: String
        get() = String.format(
            "https://www.metaweather.com/static/img/weather/png/64/$weatherStateAbbr.png",
            weatherStateAbbr
        )

    val humidityPercentage: String
        get() = "$humidity%"

    val formattedWindSpeed: String
        get() = "${windSpeed.roundToLong()} mph"

    val formattedAirPressure: String
        get() = "${airPressure.roundToLong()} mbar"

    val formattedDate: String = WeatherDateFormatter.getFormattedDayMonthYear(createdDate)
    val sunRiseDate: String = WeatherDateFormatter.getHourMinute(sunRise)
    val sunSetDate: String = WeatherDateFormatter.getHourMinute(sunSet)
}
