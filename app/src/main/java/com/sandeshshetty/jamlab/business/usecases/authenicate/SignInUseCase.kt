package com.sandeshshetty.jamlab.business.usecases.authenicate

import com.sandeshshetty.jamlab.business.data.network.ApiResponseHandler
import com.sandeshshetty.jamlab.business.data.network.ApiResult
import com.sandeshshetty.jamlab.business.data.network.abstraction.MedicalNetworkDataSource
import com.sandeshshetty.jamlab.business.data.preferences.abstraction.DataStoreRepository
import com.sandeshshetty.jamlab.business.data.preferences.util.ACCESS_TOKEN
import com.sandeshshetty.jamlab.business.data.util.safeApiCall
import com.sandeshshetty.jamlab.business.domain.state.*
import com.sandeshshetty.jamlab.framework.presentation.signin.state.SignInViewState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class SignInUseCase
@Inject
constructor(
    private val medicalNetworkDataSource: MedicalNetworkDataSource,
    private val dateStoreRepository: DataStoreRepository,
) {

    suspend fun sigInUser(
        email: String,
        password: String,
        stateEvent: StateEvent
    ): DataState<SignInViewState>? {

        val networkResult = safeApiCall(IO) {
            medicalNetworkDataSource.signInUser(email, password)
        }

        val networkResponse = object : ApiResponseHandler<SignInViewState, SignInViewState>(
            response = networkResult,
            stateEvent = stateEvent
        ) {
            override suspend fun handleSuccess(resultObj: SignInViewState): DataState<SignInViewState>? {
                var message: String? = SIGN_IN_SUCCESS
                var uiComponentType: UIComponentType = UIComponentType.None()
                return if (resultObj.token == null){
                    message = resultObj.message ?: SIGN_IN_FAILED
                    uiComponentType = UIComponentType.Toast()
                    DataState.data(
                        response = Response(
                            message = message,
                            uiComponentType = uiComponentType,
                            messageType = MessageType.Success()
                        ),
                        data = null,
                        stateEvent = stateEvent
                    )
                } else {
                    DataState.data(
                        response = Response(
                            message = message,
                            uiComponentType = uiComponentType,
                            messageType = MessageType.Success()
                        ),
                        data = resultObj,
                        stateEvent = stateEvent
                    )
                }

            }

        }.getResult()

        networkResponse?.data?.token?.let {
            dateStoreRepository.putString(ACCESS_TOKEN, it)
        }

        return networkResponse

    }

    companion object {
        val SIGN_IN_SUCCESS = "Successfully Sign In"
        val SIGN_IN_FAILED = "Error Signing In"
    }


}