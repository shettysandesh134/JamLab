package com.sandeshshetty.jamlab.framework.datasource.network.model.user

import com.google.gson.annotations.SerializedName

class LocationNetworkEntity(
    @SerializedName("id")
    val id: Int? = -1,
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("state")
    val state: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("street")
    val street: String? = null,
    @SerializedName("postcode")
    val postcode: String? = null,
    @SerializedName("lat")
    val lat: String? = null,
    @SerializedName("long")
    val long: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("status")
    val status: String? = null
) {
}