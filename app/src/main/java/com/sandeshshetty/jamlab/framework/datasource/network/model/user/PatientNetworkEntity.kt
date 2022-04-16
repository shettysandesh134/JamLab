package com.sandeshshetty.jamlab.framework.datasource.network.model.user

import com.google.gson.annotations.SerializedName

data class PatientNetworkEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("lid")
    val lid: String,
    @SerializedName("profile")
    val profile: ProfileImageNetworkEntity? =  null,
    @SerializedName("fname")
    val fname: String? = null,
    @SerializedName("lname")
    val lname: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("dob")
    val dob: String? = null,
    @SerializedName("bodytype")
    val bodytype: String? = null,
    @SerializedName("identification")
    val identification: String? = null,
    @SerializedName("occupation")
    val occupation: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("mobile")
    val mobile: String? = null,
    @SerializedName("nationality")
    val nationality: String? = null,
    @SerializedName("medicare_no")
    val medicareNo: String? = null,
    @SerializedName("medicare_ref")
    val medicareRef: String? = null,
    @SerializedName("member_no")
    val memberNo: String? = null,
    @SerializedName("member_ref")
    val memberRef: String? = null,
    @SerializedName("emmergency_relation")
    val emergencyRelation: String? = null,
    @SerializedName("emmergency_name")
    val emergencyName: String? = null,
    @SerializedName("emmergency_mobile")
    val emergencyMobile: String? = null,
    @SerializedName("private_hlth_name")
    val privateHealthName: String? = null,
    @SerializedName("private_hlth_mobile")
    val privateHealthMobile: String? = null,
    @SerializedName("private_hlth_ref")
    val privateHealthRef: String? = null,
    @SerializedName("other_hlth_name")
    val otherHealthName: String? = null,
    @SerializedName("other_hlth_mobile")
    val otherHealthMobile: String? = null,
    @SerializedName("other_hlth_ref")
    val otherHealthRef: String? = null,
    @SerializedName("verified")
    val verified: String? = null,
    @SerializedName("channel")
    val channel: String,
    @SerializedName("rating")
    val rating: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("location")
    val location: LocationNetworkEntity? = null
) {
}