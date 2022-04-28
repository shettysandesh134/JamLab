package com.sandeshshetty.jamlab.framework.datasource.network.model.consultation

import com.google.gson.annotations.SerializedName
import com.sandeshshetty.jamlab.business.domain.model.user.Location
import com.sandeshshetty.jamlab.framework.datasource.network.model.user.LocationNetworkEntity

class ClinicNetworkEntity(
    @SerializedName("clinicId")
    val clinicId: Int,

    @SerializedName("lid")
    val lid: String,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("location")
    val location: LocationNetworkEntity
) {
}