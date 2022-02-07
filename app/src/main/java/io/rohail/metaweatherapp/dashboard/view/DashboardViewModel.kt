package io.rohail.metaweatherapp.dashboard.view

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/** This [ViewModel] class is an intermediator between the Repository and View layers. */
@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {

    /**
     * static list of city names
     * future implementation will be here to show more cities
     */
    private val locationCityNames = listOf(
        "Gothenburg",
        "Stockholm",
        "Mountain View",
        "London",
        "New York",
        "Berlin"
    )

    private val _locationCityNamesFlow = MutableStateFlow(locationCityNames)
    val locationCityNamesFlow: StateFlow<List<String>> = _locationCityNamesFlow

}
