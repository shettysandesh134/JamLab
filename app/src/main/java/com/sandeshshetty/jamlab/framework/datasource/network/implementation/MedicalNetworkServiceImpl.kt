package com.sandeshshetty.jamlab.framework.datasource.network.implementation

import com.sandeshshetty.jamlab.business.domain.model.user.User
import com.sandeshshetty.jamlab.framework.datasource.network.abstraction.MedicalNetworkService
import com.sandeshshetty.jamlab.framework.datasource.network.mapper.PatientNetworkMapper
import com.sandeshshetty.jamlab.framework.datasource.network.mapper.SpecialityNetworkMapper
import com.sandeshshetty.jamlab.framework.datasource.network.repository.MedicalRepository
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterRequest
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterResponse
import com.sandeshshetty.jamlab.framework.presentation.authenticate.signin.SignInRequest
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateViewState
import com.sandeshshetty.jamlab.framework.presentation.consultation.SpecialityViewState
import com.sandeshshetty.jamlab.framework.presentation.profile.ProfileViewState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicalNetworkServiceImpl
@Inject
constructor(
 private val medicalRepository: MedicalRepository,
 private val patientNetworkMapper: PatientNetworkMapper,
 private val specialityNetworkMapper: SpecialityNetworkMapper
): MedicalNetworkService {

    override suspend fun signInUser(email: String, password: String): AuthenticateViewState {
        val user = SignInRequest(email = email,password = password)
        return medicalRepository.login(user)?.let { response ->
            AuthenticateViewState(user = response.patient?.let { patientNetworkMapper.mapFromEntity(it) }, token = response.token, message = response.message )
        }
    }

    override suspend fun registerUser(registerRequest: RegisterRequest): AuthenticateViewState {
        val response = medicalRepository.register(registerRequest)
        return AuthenticateViewState(user = null, token = response.token, message = response.message)
    }

    override suspend fun editProfile(token: String, user: User): ProfileViewState {
        val response = medicalRepository.editProfile("Bearer $token", user)
        return ProfileViewState(message = response.message, success = response.success, user = response.patient?.let { patientNetworkMapper.mapFromEntity(it) })
    }

    override suspend fun getSpecialityList(): SpecialityViewState {
        val response = medicalRepository.getSpecialities()
        return SpecialityViewState(message = response.message, success = response.success, specialities = specialityNetworkMapper.mapFromEntityList(response.specialities))
    }

}