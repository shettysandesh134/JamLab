package com.sandeshshetty.jamlab.framework.presentation.signin.state

import com.sandeshshetty.jamlab.business.domain.state.StateEvent

sealed class SignInStateEvent: StateEvent {

    class LoginUserEvent(
        val email: String,
        val password: String
    ): SignInStateEvent() {
        override fun errorInfo(): String {
            return "Error Signing in User"
        }

        override fun eventName(): String {
            return "LoginUserEvent"
        }

        override fun shouldDisplayProgressbar() = true
    }

}
