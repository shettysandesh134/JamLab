package com.sandeshshetty.jamlab.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.LocationBias
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.ktx.api.net.awaitFindAutocompletePredictions
import com.sandeshshetty.jamlab.ui.location.LocationSearchEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserLocationViewModel @Inject constructor(
    private val placesClient: PlacesClient
): ViewModel() {

    private val _searchQuery = MutableStateFlow<LocationSearchEvent>(LocationSearchEvent.Empty)

    val searchQuery
    get() = _searchQuery.asStateFlow()
    private var searchJob: Job? = null

    fun setPlaceClicked(event: LocationSearchEvent){
        _searchQuery.value = event
    }

    fun onSearchQueryChanged(query: String) = viewModelScope.launch {
        searchJob?.cancel()

        val handler = CoroutineExceptionHandler { _, throwable ->
            _searchQuery.value = LocationSearchEvent.LocationSearchEventError(throwable)
        }

        searchJob = viewModelScope.launch {
            delay(300)

            val bias: LocationBias = RectangularBounds.newInstance(
                LatLng(22.458744, 88.208162),  // SW lat, lng
                LatLng(22.730671, 88.524896) // NE lat, lng
            )

            val sessionToken = AutocompleteSessionToken.newInstance()

            val response = placesClient.awaitFindAutocompletePredictions(
                FindAutocompletePredictionsRequest.builder()
                    .setSessionToken(sessionToken)
                    .setLocationBias(bias)
                    .setTypeFilter(TypeFilter.ADDRESS)
                    .setQuery(query)
                    .setCountry("AU")
                    .build()
            )

            _searchQuery.value = LocationSearchEventFound(response.autocompletePredictions)
        }

    }



}