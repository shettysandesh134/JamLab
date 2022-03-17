package com.sandeshshetty.jamlab.framework.datasource.network.implementation

import com.sandeshshetty.jamlab.framework.datasource.network.abstraction.MedicalNetworkService
import com.sandeshshetty.jamlab.framework.datasource.network.repository.MedicalRepository
import com.sandeshshetty.jamlab.framework.presentation.signin.SignInRequest
import com.sandeshshetty.jamlab.framework.presentation.signin.state.SignInViewState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicalNetworkServiceImpl
@Inject
constructor(
 private val medicalRepository: MedicalRepository
): MedicalNetworkService {

    override suspend fun signInUser(email: String, password: String): SignInViewState {
        val user = SignInRequest(email = email,password = password)
       return medicalRepository.login(user)
    }
}