package com.sandeshshetty.jamlab.framework.presentation.profile

import com.sandeshshetty.jamlab.business.domain.model.user.User
import com.sandeshshetty.jamlab.business.domain.state.StateEvent

sealed class ProfileStateEvent: StateEvent {

    class EditUserProfileEvent(
        val user: User
    ): ProfileStateEvent() {

        override fun errorInfo(): String {
            return "Could not edit User Info"
        }

        override fun eventName(): String {
            return "EditUserProfileEvent"
        }

        override fun shouldDisplayProgressbar() = true

    }

}
