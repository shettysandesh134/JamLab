package com.sandeshshetty.jamlab.framework.presentation.common

import androidx.lifecycle.ViewModel
import com.sandeshshetty.jamlab.business.domain.state.DataState
import com.sandeshshetty.jamlab.business.domain.state.StateEvent
import com.sandeshshetty.jamlab.business.domain.state.StateMessage

abstract class BaseViewModel<ViewState>: ViewModel() {



    abstract fun setStateEvent(stateEvent: StateEvent)

    public fun launchJob(loginResult: DataState<ViewState>?, stateEvent: StateEvent) {
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
    abstract fun handleStateResponse(stateMessage: StateMessage)
}