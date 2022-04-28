package com.sandeshshetty.jamlab.framework.presentation.consultation

import com.sandeshshetty.jamlab.business.domain.model.consultation.Doctor
import com.sandeshshetty.jamlab.business.domain.model.consultation.Speciality
import com.sandeshshetty.jamlab.business.domain.state.ViewState

class DoctorListViewState(
    val message: String? = null,
    val success: Boolean? = false,
    val doctors: List<Doctor>? = null,
    val gender: String? = ""
): ViewState {
}