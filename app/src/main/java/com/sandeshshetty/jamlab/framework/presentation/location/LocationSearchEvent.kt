package com.sandeshshetty.jamlab.framework.presentation.location

import com.google.android.libraries.places.api.model.AutocompletePrediction

sealed class LocationSearchEvent {

    object Empty: LocationSearchEvent()

    data class LocationSearchEventError(
        val exception: Throwable
    ) : LocationSearchEvent()

    data class LocationSearchEventFound(
        val places: List<AutocompletePrediction>
    ): LocationSearchEvent()

}