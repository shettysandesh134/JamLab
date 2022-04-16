package com.sandeshshetty.jamlab.business.data.network.abstraction

import com.sandeshshetty.jamlab.business.domain.model.user.User
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterRequest
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterResponse
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateViewState
import com.sandeshshetty.jamlab.framework.presentation.consultation.SpecialityViewState
import com.sandeshshetty.jamlab.framework.presentation.profile.ProfileViewState

interface MedicalNetworkDataSource {

    suspend fun signInUser(email: String, password: String): AuthenticateViewState

    suspend fun registerUser(registerRequest: RegisterRequest): AuthenticateViewState

    suspend fun editProfile(token: String, user: User): ProfileViewState

    suspend fun getSpecialityList(): SpecialityViewState
}