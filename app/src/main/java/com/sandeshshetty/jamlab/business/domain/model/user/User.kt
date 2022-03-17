package com.sandeshshetty.jamlab.business.domain.model.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String,
    val lid: String,
    val profile: Profile,
    val fname: String? = null,
    val lname: String? = null,
    val description: String? = null,
    val gender: String? = null,
    val dob: String? = null,
    val bodytype: String? = null,
    val identification: String? = null,
    val occupation: String? = null,
    val email: String? = null,
    val mobile: String? = null,
    val nationality: String? = null,
    val medicareNo: String? = null,
    val medicareRef: String? = null,
    val memberNo: String? = null,
    val memberRef: String? = null,
    val emergencyRelation: String? = null,
    val emergencyName: String? = null,
    val emergencyMobile: String? = null,
    val privateHealthName: String? = null,
    val privateHealthMobile: String? = null,
    val privateHealthRef: String? = null,
    val otherHealthName: String? = null,
    val otherHealthMobile: String? = null,
    val otherHealthRef: String? = null,
    val verified: String? = null,
    val channel: String,
    val rating: String? = null,
    val type: String? = null,
    val status: String? = null,
): Parcelable{

}