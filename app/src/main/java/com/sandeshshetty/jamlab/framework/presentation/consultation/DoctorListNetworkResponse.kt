package com.sandeshshetty.jamlab.framework.presentation.consultation

import com.google.gson.annotations.SerializedName
import com.sandeshshetty.jamlab.framework.datasource.network.model.consultation.DoctorNetworkEntity

class DoctorListNetworkResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("success")
    val success: Boolean? = false,
    @SerializedName("data")
    val doctors: List<DoctorNetworkEntity>? = null
) {
}