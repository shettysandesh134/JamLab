package com.sandeshshetty.jamlab.framework.datasource.network.abstraction

import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterRequest
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterResponse
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateViewState

interface MedicalNetworkService {

    suspend fun signInUser(email: String, password: String): AuthenticateViewState

    suspend fun registerUser(registerRequest: RegisterRequest): AuthenticateViewState
}