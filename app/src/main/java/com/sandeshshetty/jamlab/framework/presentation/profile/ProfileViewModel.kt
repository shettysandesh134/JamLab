package com.sandeshshetty.jamlab.framework.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeshshetty.jamlab.business.domain.model.user.User
import com.sandeshshetty.jamlab.business.domain.state.StateEvent
import com.sandeshshetty.jamlab.business.usecases.profile.EditUserProfileUseCase
import com.sandeshshetty.jamlab.framework.presentation.authenticate.state.AuthenticateStateEvent
import com.sandeshshetty.jamlab.framework.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject
constructor(
    private val editUserProfileUseCase: EditUserProfileUseCase
): BaseViewModel<ProfileViewState>() {


    override fun setStateEvent(stateEvent: StateEvent) {
        when (stateEvent) {
            is ProfileStateEvent.EditUserProfileEvent -> {
                isLoading(stateEvent.shouldDisplayProgressbar())
                viewModelScope.launch {
                    val result = editUserProfileUseCase(stateEvent.user)
                    launchJob(result, stateEvent)
                }
            }
        }
    }

    override fun handleData(data: ProfileViewState) {

    }

}