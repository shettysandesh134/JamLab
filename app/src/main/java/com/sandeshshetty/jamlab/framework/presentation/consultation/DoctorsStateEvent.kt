package com.sandeshshetty.jamlab.framework.presentation.consultation

import com.sandeshshetty.jamlab.business.domain.model.consultation.Speciality
import com.sandeshshetty.jamlab.business.domain.state.StateEvent

sealed class DoctorsStateEvent: StateEvent {

    class GetDoctorsListEvent(
        val specality: Speciality
    ): DoctorsStateEvent() {

        override fun errorInfo(): String {
            return "Error Returning Doctors List"
        }

        override fun eventName(): String {
            return "GetDoctorsListEvent"
        }

        override fun shouldDisplayProgressbar() = true
    }

}