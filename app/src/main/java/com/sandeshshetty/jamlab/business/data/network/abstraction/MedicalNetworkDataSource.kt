package com.sandeshshetty.jamlab.business.data.network.abstraction

import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterRequest
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterResponse
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateViewState

interface MedicalNetworkDataSource {

    suspend fun signInUser(email: String, password: String): AuthenticateViewState

    suspend fun registerUser(registerRequest: RegisterRequest): AuthenticateViewState

}