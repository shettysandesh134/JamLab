package com.sandeshshetty.jamlab.business.usecases.consultation

import com.sandeshshetty.jamlab.business.data.network.ApiResponseHandler
import com.sandeshshetty.jamlab.business.data.network.abstraction.MedicalNetworkDataSource
import com.sandeshshetty.jamlab.business.data.util.safeApiCall
import com.sandeshshetty.jamlab.business.domain.model.consultation.Speciality
import com.sandeshshetty.jamlab.business.domain.state.*
import com.sandeshshetty.jamlab.framework.presentation.consultation.DoctorListViewState
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject

class GetDoctorListUseCase
@Inject
constructor(
    private val medicalNetworkDataSource: MedicalNetworkDataSource
) {

    suspend operator fun invoke(
         speciality: Speciality,
         stateEvent: StateEvent
    ): DataState<DoctorListViewState>? {

        val networkResult = safeApiCall(IO) {
            medicalNetworkDataSource.getDoctorList(speciality.name)
        }

        val networkResponse = object : ApiResponseHandler<DoctorListViewState, DoctorListViewState>(
            response = networkResult,
            stateEvent = stateEvent
        ){
            override suspend fun handleSuccess(resultObj: DoctorListViewState): DataState<DoctorListViewState>? {
                var message = SUCCESS
                var messageType: MessageType = MessageType.Success()
                var uiComponentType: UIComponentType = UIComponentType.None()

                if (resultObj.doctors == null) {
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

        return networkResponse
    }

    companion object {
        val SUCCESS = "Successfull got Doctors List"
        val FAILURE = "Error get doctors list"
    }

}