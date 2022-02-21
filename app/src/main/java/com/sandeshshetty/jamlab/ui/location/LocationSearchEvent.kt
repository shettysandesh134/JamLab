package com.sandeshshetty.jamlab.ui.location

import com.google.android.libraries.places.api.model.AutocompletePrediction
import java.lang.Exception

sealed class LocationSearchEvent {

    object Empty: LocationSearchEvent()

    data class LocationSearchEventError(
        val exception: Throwable
    ) : LocationSearchEvent()

    data class LocationSearchEventFound(
        val places: List<AutocompletePrediction>
    ): LocationSearchEvent()

}