package com.sandeshshetty.jamlab.business.data.network.implementation

import com.sandeshshetty.jamlab.business.data.network.abstraction.MedicalNetworkDataSource
import com.sandeshshetty.jamlab.framework.datasource.network.abstraction.MedicalNetworkService
import com.sandeshshetty.jamlab.framework.datasource.network.repository.MedicalRepository
import com.sandeshshetty.jamlab.framework.presentation.signin.SignInRequest
import com.sandeshshetty.jamlab.framework.presentation.signin.state.SignInViewState
import javax.inject.Inject

class MedicalNetworkDataSourceImpl
@Inject
constructor(
    private val medicalNetworkService: MedicalNetworkService
) : MedicalNetworkDataSource {

    override suspend fun signInUser(email: String, password: String): SignInViewState {
        return medicalNetworkService.signInUser(email, password)
    }
}