package io.rohail.metaweatherapp.usecase

import androidx.test.platform.app.InstrumentationRegistry
import io.rohail.metaweatherapp.api.ApiFactory
import io.rohail.metaweatherapp.api.ApiResult
import io.rohail.metaweatherapp.dashboard.data.WeatherDataSource
import io.rohail.metaweatherapp.dashboard.data.WeatherDataSourceImpl
import io.rohail.metaweatherapp.dashboard.model.LocationNameResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherRepositoryTest {

    @Test
    fun testFetchWeatherByName() = runBlocking {
        val result = getWeatherDataSource().fetchLocationByName("Gothenburg")

        assertEquals(ApiResult.Success(listOf(LocationNameResult(woeid = 890869))), result)
    }

    @Test
    fun testFetchWeatherDetail() = runBlocking {
        val result = getWeatherDataSource().fetchWeatherInfo(woeid = 890869)

        assert(result is ApiResult.Success)
    }

    private fun getWeatherDataSource(): WeatherDataSource = WeatherDataSourceImpl(
        ApiFactory.getApi(
            InstrumentationRegistry.getInstrumentation().targetContext
        )
    )

}