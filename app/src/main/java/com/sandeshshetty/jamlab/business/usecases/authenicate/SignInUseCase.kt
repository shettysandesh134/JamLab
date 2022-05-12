package com.sandeshshetty.jamlab.business.usecases.authenicate

import com.sandeshshetty.jamlab.business.data.network.ApiResponseHandler
import com.sandeshshetty.jamlab.business.data.network.abstraction.MedicalNetworkDataSource
import com.sandeshshetty.jamlab.business.data.preferences.abstraction.DataStoreRepository
import com.sandeshshetty.jamlab.business.data.preferences.util.ACCESS_TOKEN
import com.sandeshshetty.jamlab.business.data.util.safeApiCall
import com.sandeshshetty.jamlab.business.domain.state.*
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateViewState
import com.sandeshshetty.jamlab.utils.isEmailVerified
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject

class SignInUseCase
@Inject
constructor(
    private val medicalNetworkDataSource: MedicalNetworkDataSource,
    private val dataStoreRepository: DataStoreRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        stateEvent: StateEvent
    ): DataState<AuthenticateViewState>? {

        if (!email.isEmailVerified()) {
            return DataState.error(
                response = Response(
                    message = INVALID_EMAIL_ADDRESS,
                    uiComponentType = UIComponentType.Toast(),
                    messageType = MessageType.Error()
                ),
                stateEvent = null
            )
        }

        val networkResult = safeApiCall(IO) {
            medicalNetworkDataSource.signInUser(email, password)
        }

        val networkResponse = object : ApiResponseHandler<AuthenticateViewState, AuthenticateViewState>(
            response = networkResult,
            stateEvent = stateEvent
        ) {
            override suspend fun handleSuccess(resultObj: AuthenticateViewState): DataState<AuthenticateViewState>? {
                var message: String? = SIGN_IN_SUCCESS
                var uiComponentType: UIComponentType = UIComponentType.None()
                var messageType: MessageType = MessageType.Success()

                if (resultObj.token == null){
                    message = resultObj.message ?: SIGN_IN_FAILED
                    uiComponentType = UIComponentType.Toast()
                    messageType = MessageType.Error()
                }

                return DataState.data(
                        response = Response(
                            message = message,
                            uiComponentType = uiComponentType,
                            messageType = messageType
                        ),
                        data = resultObj,
                        stateEvent = stateEvent
                    )
            }

        }.getResult()

        networkResponse?.data?.token?.let {
            dataStoreRepository.putString(ACCESS_TOKEN, it)
        }

        return networkResponse
    }

    companion object {
        val SIGN_IN_SUCCESS = "Successfully Sign In"
        val SIGN_IN_FAILED = "Error Signing In"
        val INVALID_EMAIL_ADDRESS = "Invalid Email Address"
    }

}