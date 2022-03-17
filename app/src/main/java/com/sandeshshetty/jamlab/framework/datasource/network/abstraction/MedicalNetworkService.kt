package com.sandeshshetty.jamlab.framework.datasource.network.abstraction

import com.sandeshshetty.jamlab.framework.presentation.signin.state.SignInViewState

interface MedicalNetworkService {

    suspend fun signInUser(email: String, password: String): SignInViewState
}