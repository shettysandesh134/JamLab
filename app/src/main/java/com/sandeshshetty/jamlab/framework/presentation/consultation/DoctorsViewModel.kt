package com.sandeshshetty.jamlab.framework.presentation.consultation

import androidx.lifecycle.viewModelScope
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
                        doctors = it.sortedBy {
                            it.fname
                        }
                    )
                )
            }
        }
    }

    fun setGender(gender: String) {
        viewModelScope.launch {
            if (_doctorStateFlow.value.gender.equals(gender)) {
//                _doctorStateFlow.emit(DoctorListViewState(gender = ""))
                _doctorStateFlow.update {
                    DoctorListViewState(
                        message = it.message,
                        success = it.success,
                        doctors = it.doctors,
                        gender = "-"
                    )
                }
            } else {
//                _doctorStateFlow.emit(DoctorListViewState(gender = gender))
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
    }

    fun filterConfirmed() {


        val state = _doctorStateFlow.updateAndGet {
            DoctorListViewState(
                message = it.message,
                success = it.success,
                doctors = it.doctors?.filter { doctor ->
                    doctor.gender.equals(it.gender)
                },
                gender = it.gender
            )
        }

        viewModelScope.launch {
            _doctorStateFlow.emit(
                state
            )
        }



    }

}