package com.sandeshshetty.jamlab.business.usecases.consultation

import com.sandeshshetty.jamlab.business.data.network.ApiResponseHandler
import com.sandeshshetty.jamlab.business.data.network.abstraction.MedicalNetworkDataSource
import com.sandeshshetty.jamlab.business.data.util.safeApiCall
import com.sandeshshetty.jamlab.business.domain.state.*
import com.sandeshshetty.jamlab.framework.presentation.consultation.SpecialityViewState
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject

class GetSpecialityListUseCase
@Inject
constructor(
    private val medicalNetworkDataSource: MedicalNetworkDataSource
){

    suspend operator fun invoke(
        stateEvent: StateEvent?
    ): DataState<SpecialityViewState>? {

        val networkResult = safeApiCall(IO) {
            medicalNetworkDataSource.getSpecialityList()
        }

        val networkRespone = object: ApiResponseHandler<SpecialityViewState, SpecialityViewState>(
            response = networkResult,
            stateEvent = stateEvent
        ){
            override suspend fun handleSuccess(resultObj: SpecialityViewState): DataState<SpecialityViewState>? {
                var message = SUCCESS
                var messageType: MessageType = MessageType.Success()
                var uiComponentType: UIComponentType = UIComponentType.None()
                 if (resultObj.specialities == null) {
                    message = FAILURE
                     messageType = MessageType.Error()
                     uiComponentType = UIComponentType.Toast()
                }

                return DataState.data(
                    response = Response(
                        message = message,
                        messageType = messageType,
                        uiComponentType = uiComponentType
                    ),
                    data = resultObj,
                    stateEvent = stateEvent
                )

            }

        }.getResult()

        return networkRespone
    }

    companion object {
        val SUCCESS = "Successfully got Speciality List"
        val FAILURE = "Failed getting speciality list"
    }

}