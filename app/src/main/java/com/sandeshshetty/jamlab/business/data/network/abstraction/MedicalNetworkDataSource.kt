package com.sandeshshetty.jamlab.business.data.network.abstraction

import com.sandeshshetty.jamlab.framework.presentation.signin.state.SignInViewState

interface MedicalNetworkDataSource {

    suspend fun signInUser(email: String, password: String): SignInViewState
}