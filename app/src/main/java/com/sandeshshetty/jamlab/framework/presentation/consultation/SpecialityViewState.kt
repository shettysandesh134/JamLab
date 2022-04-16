package com.sandeshshetty.jamlab.framework.presentation.consultation

import com.sandeshshetty.jamlab.business.domain.model.consultation.Speciality
import com.sandeshshetty.jamlab.business.domain.state.ViewState

data class SpecialityViewState(
    val message: String? = null,
    val success: Boolean? = false,
    val specialities: List<Speciality>? = null
): ViewState {
}