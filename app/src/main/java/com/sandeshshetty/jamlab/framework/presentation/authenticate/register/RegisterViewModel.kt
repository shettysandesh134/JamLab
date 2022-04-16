package com.sandeshshetty.jamlab.framework.presentation.authenticate.register

import androidx.lifecycle.viewModelScope
import com.sandeshshetty.jamlab.business.domain.state.StateEvent
import com.sandeshshetty.jamlab.business.domain.state.StateMessage
import com.sandeshshetty.jamlab.business.usecases.authenicate.RegisterUseCase
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateStateEvent
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateViewState
import com.sandeshshetty.jamlab.framework.presentation.common.BaseViewModel
import com.sandeshshetty.jamlab.utils.printLogD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
@Inject
constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel<AuthenticateViewState>() {

    // TODO: Implement the ViewModel
    override fun setStateEvent(stateEvent: StateEvent) {
        when (stateEvent) {
            is AuthenticateStateEvent.RegisterEvent -> {
                isLoading(stateEvent.shouldDisplayProgressbar())
                viewModelScope.launch {
                    val result = registerUseCase(stateEvent.registerRequest, stateEvent)
                    launchJob(result, stateEvent)
                }
            }
        }
    }

    override fun handleData(data: AuthenticateViewState) {
        data.let { state ->

            state?.token?.let {
//                _viewState.value.copy(token = it)
                printLogD("SignInViewModelhandleData", it.toString())
            }
        }
    }

}