package com.sandeshshetty.jamlab.framework.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeshshetty.jamlab.business.domain.state.DataState
import com.sandeshshetty.jamlab.business.domain.state.Response
import com.sandeshshetty.jamlab.business.domain.state.StateEvent
import com.sandeshshetty.jamlab.business.domain.state.StateMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<ViewState>: ViewModel() {


    private val _sharedFlow = MutableSharedFlow<Response>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    val _shouldDisplayProgressBar : MutableLiveData<Boolean> = MutableLiveData()
    val shouldDisplayProgressBar: LiveData<Boolean>
        get() = _shouldDisplayProgressBar

    abstract  fun setStateEvent(stateEvent: StateEvent)

    fun launchJob(result: DataState<ViewState>?, stateEvent: StateEvent) {
        result?.let { dataState ->
            dataState.data?.let {
                handleData(it)
            }
            dataState.stateMessage?.let {
                handleStateResponse(it)
            }
        }
    }

    fun isLoading(isLoading: Boolean) {
        _shouldDisplayProgressBar.value = isLoading
    }

    abstract fun handleData(data: ViewState)

     fun handleStateResponse(stateMessage: StateMessage) {
         viewModelScope.launch {
             isLoading(false)
             _sharedFlow.emit(stateMessage.response)
         }
     }
}