package com.sandeshshetty.jamlab.business.domain.model.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val id: Int? = -1,
    val address: String? = null,
    val country: String? = null,
    val state: String? = null,
    val city: String? = null,
    val street: String? = null,
    val postcode: String? = null,
    val lat: String? = null,
    val long: String? = null,
    val type: String? = null,
    val status: String? = null
): Parcelable {
}