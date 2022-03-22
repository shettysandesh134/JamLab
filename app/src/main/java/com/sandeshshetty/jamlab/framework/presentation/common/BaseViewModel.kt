package com.sandeshshetty.jamlab.framework.presentation.common

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

    abstract fun setStateEvent(stateEvent: StateEvent)

    fun launchJob(loginResult: DataState<ViewState>?, stateEvent: StateEvent) {
        loginResult?.let { dataState ->
            dataState.data?.let {
                handleData(it)
            }
            dataState.stateMessage?.let {
                handleStateResponse(it)
            }
        }
    }



    abstract fun handleData(data: ViewState)

     fun handleStateResponse(stateMessage: StateMessage) {
         viewModelScope.launch {
             _sharedFlow.emit(stateMessage.response)
         }
     }
}