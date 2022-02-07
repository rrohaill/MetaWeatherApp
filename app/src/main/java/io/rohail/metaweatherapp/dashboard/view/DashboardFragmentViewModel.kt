package io.rohail.metaweatherapp.dashboard.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.rohail.metaweatherapp.dashboard.model.WeatherInfoUIResult
import io.rohail.metaweatherapp.dashboard.usecase.FetchWeatherUseCase
import io.rohail.metaweatherapp.dashboard.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** This [ViewModel] class is an intermediator between the Repository and View layers. */
@HiltViewModel
class DashboardFragmentViewModel @Inject constructor(
    private val fetchWeatherUseCase: FetchWeatherUseCase,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private var locationName: String = ""

    fun getWeatherResultFlow():Flow<WeatherInfoUIResult> = getWeatherUseCase(locationName = locationName)

    fun fetchWeatherData() {
        viewModelScope.launch {
            fetchWeatherUseCase(locationName)
        }
    }


    fun setLocationName(location: String) {
        locationName = location
    }

}