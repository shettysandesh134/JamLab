package com.sandeshshetty.jamlab.framework.presentation.consultation

import com.google.gson.annotations.SerializedName
import com.sandeshshetty.jamlab.business.domain.model.consultation.Speciality
import com.sandeshshetty.jamlab.framework.datasource.network.model.consultation.SpecialityNetworkEntity

class SpecialityResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("success")
    val success: Boolean? = false,
    @SerializedName("data")
    val specialities: List<SpecialityNetworkEntity>? = null
) {
}