package com.sandeshshetty.jamlab.framework.datasource.network.model.consultation

import com.google.gson.annotations.SerializedName

class SymptomNetworkEntity(
    @SerializedName("id")
    val id: Int,

    @SerializedName("did")
    val did: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("status")
    val status: String
) {
}