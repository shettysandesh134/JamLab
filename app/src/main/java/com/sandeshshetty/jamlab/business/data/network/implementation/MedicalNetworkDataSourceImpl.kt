package com.sandeshshetty.jamlab.business.data.network.implementation

import com.sandeshshetty.jamlab.business.data.network.abstraction.MedicalNetworkDataSource
import com.sandeshshetty.jamlab.business.domain.model.user.User
import com.sandeshshetty.jamlab.framework.datasource.network.abstraction.MedicalNetworkService
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterRequest
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterResponse
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateViewState
import com.sandeshshetty.jamlab.framework.presentation.consultation.SpecialityViewState
import com.sandeshshetty.jamlab.framework.presentation.profile.ProfileViewState
import javax.inject.Inject

class MedicalNetworkDataSourceImpl
@Inject
constructor(
    private val medicalNetworkService: MedicalNetworkService
) : MedicalNetworkDataSource {

    override suspend fun signInUser(email: String, password: String): AuthenticateViewState {
        return medicalNetworkService.signInUser(email, password)
    }

    override suspend fun registerUser(registerRequest: RegisterRequest): AuthenticateViewState {
        return medicalNetworkService.registerUser(registerRequest)
    }

    override suspend fun editProfile(token: String, user: User): ProfileViewState {
        return medicalNetworkService.editProfile(token, user)
    }

    override suspend fun getSpecialityList(): SpecialityViewState {
        return medicalNetworkService.getSpecialityList()
    }
}