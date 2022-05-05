package com.sandeshshetty.jamlab.framework.presentation.consultation

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sandeshshetty.jamlab.business.domain.model.consultation.Doctor
import com.sandeshshetty.jamlab.business.domain.state.StateEvent
import com.sandeshshetty.jamlab.business.usecases.consultation.GetDoctorListUseCase
import com.sandeshshetty.jamlab.framework.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorsViewModel
@Inject
constructor(
    private val getDoctorListUseCase: GetDoctorListUseCase
) : BaseViewModel<DoctorListViewState>() {

    private val _doctorStateFlow = MutableStateFlow(DoctorListViewState())
    val doctorStateFlow get() = _doctorStateFlow.asStateFlow()

    private var _doctorViewState = DoctorListViewState()

    override fun setStateEvent(stateEvent: StateEvent) {
        when (stateEvent) {
            is DoctorsStateEvent.GetDoctorsListEvent -> {
                viewModelScope.launch {
                    isLoading(stateEvent.shouldDisplayProgressbar())
                    val result = getDoctorListUseCase(stateEvent.specality, stateEvent)
                    launchJob(result, stateEvent)
                }

            }
        }
    }

    override fun handleData(data: DoctorListViewState) {
        data.doctors?.let {
            viewModelScope.launch {
                _doctorStateFlow.emit(
                    DoctorListViewState(
                        message = data.message,
                        success = data.success,
                        doctors = data.doctors,
                        filteredDoctors = it.sortedBy {
                            it.fname
                        }

                    )
                )
            }
        }
    }

//    override fun handleData(data: DoctorListViewState) {
//        data.doctors?.let {
//            _doctorViewState = DoctorListViewState(
//                message = data.message,
//                success = data.success,
//                doctors = it.sortedBy {
//                    it.fname
//                }
//            )
//        }
//
//    }

//    fun displayDoctorsList() {
//        viewModelScope.launch {
//            _doctorStateFlow.emit(
//                _doctorViewState
//            )
//        }
//    }

    fun setGender(gender: String) {

            if (_doctorStateFlow.value.gender.equals(gender)) {
                // Doing this way does not update the UI of checkbox as needed so we are using update
//                _doctorStateFlow.value.gender = "-"
                _doctorStateFlow.update {
                    DoctorListViewState(
                        message = it.message,
                        success = it.success,
                        doctors = it.doctors,
                        gender = "-"
                    )
                }
            } else {
                _doctorStateFlow.update {
                    DoctorListViewState(
                        message = it.message,
                        success = it.success,
                        doctors = it.doctors,
                        gender = gender
                    )
                }
            }
    }

    fun filterConfirmed() {


        val stateValue = _doctorStateFlow.value

//        if (stateValue.gender.equals("M") || stateValue.gender.equals("F")){
//            _doctorStateFlow.value.filteredDoctors = stateValue.doctors?.filter {
//                it.gender.equals(stateValue.gender)
//            }?.sortedBy {
//                it.fname
//            }
//        }

        val filteredList = if(stateValue.gender.equals("-")) {
            stateValue.doctors?.sortedBy {
                    it.fname
                }
        }else {
            stateValue.doctors?.filter {
                    it.gender.equals(stateValue.gender)
                }?.sortedBy {
                    it.fname
                }
        }


//            _doctorStateFlow.updateAndGet {
//            DoctorListViewState(
//                message = it.message,
//                success = it.success,
//                doctors = it.doctors,
//                gender = it.gender,
//                filteredDoctors = it.doctors?.filter { doctor ->
//                    doctor.gender.equals(it.gender)
//                }
//            )
//        }

        _doctorStateFlow.value = DoctorListViewState(
            message = stateValue.message,
            success = stateValue.success,
            doctors = stateValue.doctors,
            filteredDoctors = filteredList,
            gender = stateValue.gender
        )
    }

}