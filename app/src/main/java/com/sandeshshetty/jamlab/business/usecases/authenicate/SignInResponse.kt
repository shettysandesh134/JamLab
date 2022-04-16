package com.sandeshshetty.jamlab.business.usecases.authenicate

import com.google.gson.annotations.SerializedName
import com.sandeshshetty.jamlab.framework.datasource.network.model.user.PatientNetworkEntity

data class SignInResponse(
    @SerializedName("profile")
    var patient: PatientNetworkEntity? = null,

    @SerializedName("access_token")
    var token: String? = null,

    @SerializedName("message")
    var message: String? = null
) {

}