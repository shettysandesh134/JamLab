package com.sandeshshetty.jamlab.framework.presentation.signin.state

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sandeshshetty.jamlab.business.domain.model.user.Profile
import com.sandeshshetty.jamlab.business.domain.state.ViewState
import kotlinx.android.parcel.Parcelize


data class SignInViewState(
    @SerializedName("user")
    var profile: Profile? = null,

    @SerializedName("access_token")
    var token: String? = null,

    @SerializedName("message")
    var message: String? = null
): ViewState {
}