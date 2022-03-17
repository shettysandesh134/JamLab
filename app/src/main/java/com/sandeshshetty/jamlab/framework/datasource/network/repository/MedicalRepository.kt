package com.sandeshshetty.jamlab.framework.datasource.network.repository

import com.sandeshshetty.jamlab.framework.presentation.signin.SignInRequest
import com.sandeshshetty.jamlab.framework.presentation.signin.state.SignInViewState
import retrofit2.http.*

interface MedicalRepository {

    @Headers("content-type: application/json")
    @POST("/api/login")
    suspend fun login(
        @Body user: SignInRequest
    ): SignInViewState

}