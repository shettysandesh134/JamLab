package com.sandeshshetty.jamlab.framework.datasource.network.model.user

import com.google.gson.annotations.SerializedName

data class ProfileImageNetworkEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("referenceid")
    val referenceId: String,
    @SerializedName("reference")
    val reference: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("extention")
    val extention: String,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("filesize")
    val fileSize: String,
    @SerializedName("filedata")
    val fileData: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("status")
    val status: String
) {
}