package com.sandeshshetty.jamlab.business.usecases.profile

import com.sandeshshetty.jamlab.business.data.network.ApiResponseHandler
import com.sandeshshetty.jamlab.business.data.network.abstraction.MedicalNetworkDataSource
import com.sandeshshetty.jamlab.business.data.preferences.abstraction.DataStoreRepository
import com.sandeshshetty.jamlab.business.data.preferences.util.ACCESS_TOKEN
import com.sandeshshetty.jamlab.business.data.util.safeApiCall
import com.sandeshshetty.jamlab.business.domain.model.user.User
import com.sandeshshetty.jamlab.business.domain.state.*
import com.sandeshshetty.jamlab.framework.presentation.profile.ProfileViewState
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject

class EditUserProfileUseCase
@Inject
constructor(
    private val medicalNetworkDataSource: MedicalNetworkDataSource,
    private val dataStoreRepository: DataStoreRepository
) {

    suspend operator fun invoke(
        user: User
    ): DataState<ProfileViewState>? {

        val accessToken = dataStoreRepository.getString(ACCESS_TOKEN)

        val networkResult = safeApiCall(IO){
            accessToken?.let {
                medicalNetworkDataSource.editProfile(accessToken, user)
            }
        }

        val networkResponse = object: ApiResponseHandler<ProfileViewState, ProfileViewState>(
            response = networkResult,
            stateEvent = null
        ){
            override suspend fun handleSuccess(resultObj: ProfileViewState): DataState<ProfileViewState>? {
                return DataState.data(
                    response = Response(
                        message = EDIT_USER_SUCCESS,
                        messageType = MessageType.Success(),
                        uiComponentType = UIComponentType.None()
                    ),
                    data = resultObj,
                    stateEvent = null
                )
            }

        }.getResult()

        return networkResponse
    }

    companion object {
        const val EDIT_USER_SUCCESS = "User Edited Successfully"
    }

}