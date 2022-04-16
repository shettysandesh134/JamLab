package com.sandeshshetty.jamlab.framework.datasource.network.repository

import com.sandeshshetty.jamlab.business.domain.model.user.User
import com.sandeshshetty.jamlab.business.usecases.authenicate.SignInResponse
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterRequest
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterResponse
import com.sandeshshetty.jamlab.framework.presentation.authenticate.signin.SignInRequest
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateViewState
import com.sandeshshetty.jamlab.framework.presentation.consultation.SpecialityResponse
import com.sandeshshetty.jamlab.framework.presentation.consultation.SpecialityViewState
import com.sandeshshetty.jamlab.framework.presentation.profile.ProfileResponse
import retrofit2.http.*

interface MedicalRepository {

    @Headers("content-type: application/json")
    @POST("/api/login")
    suspend fun login(
        @Body user: SignInRequest
    ): SignInResponse

    @Headers("content-type: application/json", "Accept: application/json")
    @POST("/api/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): SignInResponse

    @Headers("content-type: application/json", "Accept: application/json")
    @POST("/api/patient/profile")
    suspend fun editProfile(
        @Header("Authorization") token: String,
        @Body user: User
    ): ProfileResponse

    @Headers("content-type: application/json", "Accept: application/json")
    @GET("/api/public/specialities")
    suspend fun getSpecialities(): SpecialityResponse

}