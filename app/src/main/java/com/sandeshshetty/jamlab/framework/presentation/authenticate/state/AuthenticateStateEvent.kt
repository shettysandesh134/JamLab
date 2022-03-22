package com.sandeshshetty.jamlab.framework.presentation.authenticate.state

import com.sandeshshetty.jamlab.business.domain.state.StateEvent
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterRequest

sealed class AuthenticateStateEvent: StateEvent {

    class LoginUserEvent(
        val email: String,
        val password: String
    ): AuthenticateStateEvent() {
        override fun errorInfo(): String {
            return "Error Signing in User"
        }

        override fun eventName(): String {
            return "LoginUserEvent"
        }

        override fun shouldDisplayProgressbar() = true
    }

    class RegisterEvent(
        val registerRequest: RegisterRequest
    ): AuthenticateStateEvent() {
        override fun errorInfo(): String {
            return "Error Registering User"
        }

        override fun eventName(): String {
            return "RegisterEvent"
        }

        override fun shouldDisplayProgressbar() = true

    }

}
