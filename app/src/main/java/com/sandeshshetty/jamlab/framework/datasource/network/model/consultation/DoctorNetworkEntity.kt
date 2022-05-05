package com.sandeshshetty.jamlab.framework.datasource.network.model.consultation

import com.google.gson.annotations.SerializedName
import com.sandeshshetty.jamlab.business.domain.model.consultation.Clinic
import com.sandeshshetty.jamlab.business.domain.model.consultation.Qualification
import com.sandeshshetty.jamlab.business.domain.model.consultation.Symptom
import com.sandeshshetty.jamlab.framework.datasource.network.model.user.LocationNetworkEntity

class DoctorNetworkEntity(
    @SerializedName("id")
    val id: Int,

    @SerializedName("lid")
    val lid: Int,

//   val profile:
    @SerializedName("fname")
    val fname: String? = null,

    @SerializedName("lname")
    val lname: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("doctortype")
    val doctortype: String? = null,

    @SerializedName("website")
    val website: String? = null,

    @SerializedName("gender")
    val gender: String? = null,

    @SerializedName("dob")
    val dob: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("practitioner_registration_number")
    val practionerRegistrationNumber: String? = null,

    @SerializedName("experience")
    val experience: String? = null,

    @SerializedName("language")
    val language: String? = null,

    @SerializedName("timezone")
    val timezone: String? = null,

    @SerializedName("digital_id_status")
    val digitalIdStatus: String? = null,

    @SerializedName("police_check_status")
    val policeCheckStatus: String? = null,

    @SerializedName("price")
    val price: String? = null,

    @SerializedName("rating")
    val rating: String? = null,

    @SerializedName("channel")
    val channel: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("relevance")
    val relevance: String? = null,

    @SerializedName("location")
    val location: LocationNetworkEntity? = null,

    @SerializedName("clinics")
    val clinics: List<ClinicNetworkEntity>? = null,

    @SerializedName("speciality")
    val specialities: List<SpecialityNetworkEntity>? = null,

    @SerializedName("symptoms")
    val symptoms: List<SymptomNetworkEntity>? = null,

    @SerializedName("qualifications")
    val qualifications: List<QualificationNetworkEntity>? = null
) {
}