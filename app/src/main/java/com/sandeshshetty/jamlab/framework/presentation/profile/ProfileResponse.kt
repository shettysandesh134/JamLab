package com.sandeshshetty.jamlab.framework.presentation.profile

import com.google.gson.annotations.SerializedName
import com.sandeshshetty.jamlab.framework.datasource.network.mapper.PatientNetworkMapper
import com.sandeshshetty.jamlab.framework.datasource.network.model.user.PatientNetworkEntity

data class ProfileResponse(
    @SerializedName("message")
    var message: String? = null,

    @SerializedName("success")
    var success: Boolean = false,

    @SerializedName("data")
    var patient: PatientNetworkEntity? = null
) {
}