package com.sandeshshetty.jamlab.framework.presentation.consultation

import androidx.lifecycle.viewModelScope
import com.sandeshshetty.jamlab.business.domain.state.StateEvent
import com.sandeshshetty.jamlab.business.usecases.consultation.GetSpecialityListUseCase
import com.sandeshshetty.jamlab.framework.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
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

//    private val _specialityShareFlow = MutableSharedFlow<SpecialityStateEvent>()
//    val specialitySharedFlow get() = _specialityShareFlow.asSharedFlow()

    private val _specialityEventChannel = Channel<SpecialityStateEvent>()
    val specialityEventChannel = _specialityEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            isLoading(SpecialityStateEvent.GetSpecialityListEvent().shouldDisplayProgressbar())
            val result = getSpecialityListUseCase(SpecialityStateEvent.GetSpecialityListEvent())
            launchJob(result, SpecialityStateEvent.GetSpecialityListEvent())
        }
    }

    override fun setStateEvent(stateEvent: StateEvent) {
        when (stateEvent) {
            is SpecialityStateEvent.OnSpecialityClickEvent -> {
                viewModelScope.launch {
//                    _specialityShareFlow.emit(stateEvent)
                    _specialityEventChannel.send(stateEvent)
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