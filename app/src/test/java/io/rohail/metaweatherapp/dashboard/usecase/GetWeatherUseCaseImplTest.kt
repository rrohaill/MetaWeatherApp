package io.rohail.metaweatherapp.dashboard.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.rohail.metaweatherapp.dashboard.data.WeatherRepository
import io.rohail.metaweatherapp.dashboard.data.model.ConsolidatedWeather
import io.rohail.metaweatherapp.dashboard.data.model.Parent
import io.rohail.metaweatherapp.dashboard.data.model.WeatherInfo
import io.rohail.metaweatherapp.dashboard.model.*
import io.rohail.metaweatherapp.dashboard.usecase.GetWeatherUseCaseImpl
import io.rohail.metaweatherapp.utilities.toUI
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetWeatherUseCaseImplTest {

    private val successResult = WeatherInfoResult.Success(
        data = getDummyWeatherInfo()
    )
    private val errorResult = WeatherInfoResult.Error("Error")

    private val successUiResult = WeatherInfoUIResult.Success(getDummyWeatherInfoUI())
    private val errorUiResult = WeatherInfoUIResult.Error("Error")

    private val locationName = "Gothenburg"


    @Test
    fun `get weather ui result success`() = runBlocking {
        val uc = GetWeatherUseCaseImpl(weatherRepository = getWeatherRepoMock(true))

        assertEquals(successUiResult, uc(locationName = locationName).first())
    }

    @Test
    fun `get weather ui result error`() = runBlocking {
        val uc = GetWeatherUseCaseImpl(weatherRepository = getWeatherRepoMock(false))

        assertEquals(errorUiResult, uc(locationName = locationName).first())
    }

    private fun getWeatherRepoMock(hasValue: Boolean): WeatherRepository = mock {
        onBlocking {
            getWeatherResult(locationName = locationName)
        } doReturn if (hasValue) flow { emit(successResult) } else flow { emit(errorResult) }
    }

    /** Instantiates a dummy test double object for [WeatherInfo].
     * @return The dummy test double object. */
    private fun getDummyWeatherInfo(): WeatherInfo =
        WeatherInfo(
            consolidatedWeather = listOf(getDummyConsolidateItem(), getDummyConsolidateItem()),
            time = "2022-02-07T07:44:06.398517+01:00",
            sunRise = "2022-02-07T07:44:06.398517+01:00",
            sunSet = "2022-02-07T07:44:06.398517+01:00",
            timezone = "",
            parent = Parent(title = "", locationType = "", woeid = Int.MIN_VALUE, lattLong = ""),
            timezoneName = "",
            sources = emptyList(),
            title = "",
            locationType = "",
            woeid = Long.MIN_VALUE,
            lattLong = ""
        )

    private fun getDummyConsolidateItem(): ConsolidatedWeather =
        ConsolidatedWeather(
            0L, "", "", "",
            "2022-02-07T07:44:06.398517+01:00", "", 0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0, 0.0, 0
        )

    /** Instantiates a dummy test double object for [WeatherInfoUI].
     * @return The dummy test double object. */
    private fun getDummyWeatherInfoUI(): WeatherInfoUI =
        getDummyWeatherInfo().toUI()
}