package com.sandeshshetty.jamlab.business.usecases.authenicate

import com.sandeshshetty.jamlab.business.data.network.ApiResponseHandler
import com.sandeshshetty.jamlab.business.data.network.abstraction.MedicalNetworkDataSource
import com.sandeshshetty.jamlab.business.data.preferences.abstraction.DataStoreRepository
import com.sandeshshetty.jamlab.business.data.util.safeApiCall
import com.sandeshshetty.jamlab.business.domain.state.*
import com.sandeshshetty.jamlab.business.usecases.authenicate.SignInUseCase.Companion.INVALID_EMAIL_ADDRESS
import com.sandeshshetty.jamlab.framework.presentation.authenticate.register.RegisterRequest
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateViewState
import com.sandeshshetty.jamlab.utils.Constants.ACCESS_TOKEN
import com.sandeshshetty.jamlab.utils.isEmailVerified
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject

class RegisterUseCase
@Inject
constructor(
    private val medicalNetworkDataSource: MedicalNetworkDataSource,
    private val dataStoreRepository: DataStoreRepository
) {

    suspend operator fun invoke(
        registerRequest: RegisterRequest,
        stateEvent: StateEvent
    ): DataState<AuthenticateViewState>? {

        val dataError = validateFields(registerRequest)
        if (dataError != null) {
            return dataError
        }

        val networkResult = safeApiCall(IO) {
            medicalNetworkDataSource.registerUser(registerRequest)
        }

        val networkResponse = object :
            ApiResponseHandler<AuthenticateViewState, AuthenticateViewState>(
                response = networkResult,
                stateEvent = stateEvent
            ) {
            override suspend fun handleSuccess(resultObj: AuthenticateViewState): DataState<AuthenticateViewState>? {
                var message: String = REGISTER_SUCCESS
                var uiComponentType: UIComponentType = UIComponentType.None()
                return if (resultObj.token == null) {
                    message = REGISTER_FAILED
                    uiComponentType = UIComponentType.Toast()
                    DataState.data(
                        response = Response(
                            message = message,
                            messageType = MessageType.Error(),
                            uiComponentType = uiComponentType
                        ),
                        data = null,
                        stateEvent = stateEvent
                    )
                } else {
                    DataState.data(
                        response = Response(
                            message = message,
                            messageType = MessageType.Success(),
                            uiComponentType = uiComponentType
                        ),
                        data = resultObj,
                        stateEvent = stateEvent
                    )
                }
            }

        }.getResult()

        networkResponse?.data?.token?.let {
            dataStoreRepository.putString(ACCESS_TOKEN, it)
        }

        return networkResponse
    }

    private fun validateFields(registerRequest: RegisterRequest): DataState<AuthenticateViewState>? {
        var message = "Reason: "
        var messageType : MessageType = MessageType.Success()

        if (registerRequest.name?.isEmpty()) {
            messageType = MessageType.Error()
            message += NAME_FIELD_EMPTY
        }
        if (!registerRequest.email?.isEmailVerified()) {
            messageType = MessageType.Error()
            message += INVALID_EMAIL_ADDRESS
        }
        if (!registerRequest.password?.equals(registerRequest.password_confirmation)) {
            messageType = MessageType.Error()
            message += CONFIRM_PWD_ERROR
        }

        when (messageType) {
            is MessageType.Success -> {

            }
            is MessageType.Error -> {
                return DataState.error(
                    response = Response(
                        message = message,
                        messageType = messageType,
                        uiComponentType = UIComponentType.Toast()
                    ),
                    stateEvent = null
                )
            }
        }

        return null

    }

    companion object {
        val REGISTER_SUCCESS = "User successfully registered"
        val REGISTER_FAILED = "Failed registering user"
        val NAME_FIELD_EMPTY = "Name Field cannot be empty"
        val CONFIRM_PWD_ERROR = "Password confirmation failed"
    }

}