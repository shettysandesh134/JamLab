package com.sandeshshetty.jamlab.business.domain.model.consultation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Qualification(
    val id: Int,
    val did: String,
    val fid: String,
    val name: String,
    val description: String,
    val type: String,
    val status: String,
    val fileName: String? = null
):Parcelable {
}