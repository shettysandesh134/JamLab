package com.sandeshshetty.jamlab.framework.presentation.signin

import androidx.lifecycle.viewModelScope
import com.sandeshshetty.jamlab.business.domain.state.MessageType
import com.sandeshshetty.jamlab.business.domain.state.Response
import com.sandeshshetty.jamlab.business.domain.state.StateEvent
import com.sandeshshetty.jamlab.business.domain.state.StateMessage
import com.sandeshshetty.jamlab.business.usecases.authenicate.SignInUseCase
import com.sandeshshetty.jamlab.framework.presentation.common.BaseViewModel
import com.sandeshshetty.jamlab.framework.presentation.signin.state.SignInStateEvent
import com.sandeshshetty.jamlab.framework.presentation.signin.state.SignInViewState
import com.sandeshshetty.jamlab.utils.printLogD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninViewModel
@Inject
constructor(
    private val signInUseCase: SignInUseCase
): BaseViewModel<SignInViewState>() {

    private val _viewState = MutableStateFlow(SignInViewState())
    val viewState = _viewState.asStateFlow()

    private val _signInShareFlow = MutableSharedFlow<Response>()
    val signInSharedFlow = _signInShareFlow.asSharedFlow()

    override fun setStateEvent(stateEvent: StateEvent) {
       when (stateEvent) {
            is SignInStateEvent.LoginUserEvent -> {
                viewModelScope.launch {
                    val loginResult =
                        signInUseCase.sigInUser(stateEvent.email, stateEvent.password, stateEvent)
//                    loginResult.let {
//                        printLogD(
//                            "\"SignInViewModelGE\"",
//                            it?.stateMessage?.response?.message.toString()
//                        )
//                    }
                    launchJob(loginResult, stateEvent)
                }
            }
        }

    }

    override fun handleData(data: SignInViewState) {
        data.let { state ->

            state?.token?.let {
                _viewState.value.copy(token = it)
                printLogD("SignInViewModelhandleData", it.toString())
            }
        }
    }

    override fun handleStateResponse(stateMessage: StateMessage) {
        viewModelScope.launch {
            _signInShareFlow.emit(stateMessage.response)
        }
    }


}