package com.sandeshshetty.jamlab.framework.presentation.consultation

import com.sandeshshetty.jamlab.business.domain.model.consultation.Speciality
import com.sandeshshetty.jamlab.business.domain.state.StateEvent

sealed class SpecialityStateEvent: StateEvent {

    class OnSpecialityClickEvent(
        val speciality: Speciality
    ): SpecialityStateEvent() {

        override fun errorInfo(): String {
            return "Blank speciality clicked"
        }

        override fun eventName(): String {
            return "OnSpecialityClick"
        }

        override fun shouldDisplayProgressbar() = true

    }

    class GetSpecialityListEvent(): SpecialityStateEvent() {
        override fun errorInfo(): String {
            return "List not found"
        }

        override fun eventName(): String {
            return "GetSpecialityList"
        }

        override fun shouldDisplayProgressbar() = true

    }

}
