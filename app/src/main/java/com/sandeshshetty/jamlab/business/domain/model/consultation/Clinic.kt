package com.sandeshshetty.jamlab.business.domain.model.consultation

import android.os.Parcelable
import com.sandeshshetty.jamlab.business.domain.model.user.Location
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Clinic(
    val clinicId: Int,
    val lid: String,
    val name: String? = null,
    val description: String? = null,
    val type: String? = null,
    val status: String? = null,
    val location: Location
): Parcelable {
}