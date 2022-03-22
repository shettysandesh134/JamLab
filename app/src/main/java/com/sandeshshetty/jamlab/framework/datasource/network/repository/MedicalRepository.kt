package com.sandeshshetty.jamlab.framework.datasource.network.repository

import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterRequest
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterResponse
import com.sandeshshetty.jamlab.framework.presentation.authenticate.signin.SignInRequest
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateViewState
import retrofit2.http.*

interface MedicalRepository {

    @Headers("content-type: application/json")
    @POST("/api/login")
    suspend fun login(
        @Body user: SignInRequest
    ): AuthenticateViewState

    @Headers("content-type: application/json", "Accept: application/json")
    @POST("/api/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): AuthenticateViewState

}