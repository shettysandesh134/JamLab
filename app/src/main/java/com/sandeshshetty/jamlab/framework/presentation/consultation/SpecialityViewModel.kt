package com.sandeshshetty.jamlab.framework.presentation.consultation

import androidx.lifecycle.viewModelScope
import com.sandeshshetty.jamlab.business.domain.state.StateEvent
import com.sandeshshetty.jamlab.business.usecases.consultation.GetSpecialityListUseCase
import com.sandeshshetty.jamlab.framework.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecialityViewModel
@Inject
constructor(
    private val getSpecialityListUseCase: GetSpecialityListUseCase
): BaseViewModel<SpecialityViewState>() {

    private val _specialityViewState = MutableStateFlow(SpecialityViewState())
    val specialityViewState get() = _specialityViewState.asStateFlow()

    private val _specialityShareFlow = MutableSharedFlow<SpecialityStateEvent>()
    val specialitySharedFlow get() = _specialityShareFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            val result = getSpecialityListUseCase(SpecialityStateEvent.GetSpecialityList())
            launchJob(result, SpecialityStateEvent.GetSpecialityList())
        }
    }

    override fun setStateEvent(stateEvent: StateEvent) {
        when (stateEvent) {
            is SpecialityStateEvent.OnSpecialityClick -> {
                viewModelScope.launch {
                    _specialityShareFlow.emit(stateEvent)
                }

            }
        }
    }

    override fun handleData(data: SpecialityViewState) {
        data.let { state ->
            state.specialities?.let {
                viewModelScope.launch {
                    _specialityViewState.emit(SpecialityViewState(data.message,data.success, data.specialities))
                }

            }
        }
    }
}