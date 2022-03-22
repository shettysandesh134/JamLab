package com.sandeshshetty.jamlab.framework.datasource.network.implementation

import com.sandeshshetty.jamlab.framework.datasource.network.abstraction.MedicalNetworkService
import com.sandeshshetty.jamlab.framework.datasource.network.repository.MedicalRepository
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterRequest
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterResponse
import com.sandeshshetty.jamlab.framework.presentation.authenticate.signin.SignInRequest
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateViewState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicalNetworkServiceImpl
@Inject
constructor(
 private val medicalRepository: MedicalRepository
): MedicalNetworkService {

    override suspend fun signInUser(email: String, password: String): AuthenticateViewState {
        val user = SignInRequest(email = email,password = password)
       return medicalRepository.login(user)
    }

    override suspend fun registerUser(registerRequest: RegisterRequest): AuthenticateViewState {
        return medicalRepository.register(registerRequest)
    }
}