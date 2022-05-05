package com.sandeshshetty.jamlab.framework.presentation.consultation

import com.sandeshshetty.jamlab.business.domain.model.consultation.Doctor
import com.sandeshshetty.jamlab.business.domain.model.consultation.Speciality
import com.sandeshshetty.jamlab.business.domain.state.ViewState

data class DoctorListViewState(
    val message: String? = null,
    val success: Boolean? = false,
    val doctors: List<Doctor>? = null,
    var filteredDoctors: List<Doctor>? = null,
    var gender: String? = "-"
): ViewState {

}