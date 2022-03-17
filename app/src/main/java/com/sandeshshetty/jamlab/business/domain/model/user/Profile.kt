package com.sandeshshetty.jamlab.business.domain.model.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(
    val id: String,
    val uid: String,
    val referenceId: String,
    val reference: String,
    val name: String,
    val description: String,
    val extention: String,
    val uri: String,
    val fileSize: String,
    val fileData: String,
    val type: String,
    val status: String
): Parcelable {
}