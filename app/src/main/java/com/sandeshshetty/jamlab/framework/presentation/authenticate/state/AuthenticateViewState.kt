package com.sandeshshetty.jamlab.framework.presentation.authenticate.state

import com.google.gson.annotations.SerializedName
import com.sandeshshetty.jamlab.business.domain.model.user.User
import com.sandeshshetty.jamlab.business.domain.state.ViewState
import com.sandeshshetty.jamlab.framework.datasource.network.model.user.PatientNetworkEntity


data class AuthenticateViewState(
    val user: User? = null,
    var token: String? = null,
    var message: String? = null
): ViewState {
}