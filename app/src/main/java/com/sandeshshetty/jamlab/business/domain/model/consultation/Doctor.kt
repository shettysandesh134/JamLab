package com.sandeshshetty.jamlab.business.domain.model.consultation

import android.os.Parcelable
import com.sandeshshetty.jamlab.business.domain.model.user.Location
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Doctor(
    val id: Int,
    val lid: Int,
//    val profile:
    val fname: String? = null,
    val lname: String? = null,
    val description: String? = null,
    val doctortype: String? = null,
    val website: String? = null,
    val gender: String? = null,
    val dob: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val practionerRegistrationNumber: String? = null,
    val experience: String? = null,
    val language: String? = null,
    val timezone: String? = null,
    val digitalIdStatus: String? = null,
    val policeCheckStatus: String? = null,
    val price: String? = null,
    val rating: String? = null,
    val channel: String? = null,
    val type: String? = null,
    val status: String? = null,
    val relevance: String? = null,
    val location: Location? = null,
    val clinics: List<Clinic>? = null,
    val specialities: List<Speciality>? = null,
    val symptoms: List<Symptom>? = null,
    val qualifications: List<Qualification>? = null
): Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Doctor
        if (id != other.id) return false
        if (lid != other.lid) return false
        if (lid != other.lid) return false
        if (email != other.email) return false
        if (practionerRegistrationNumber != other.practionerRegistrationNumber) return false
        if (channel != other.channel) return false

        return true
    }

}