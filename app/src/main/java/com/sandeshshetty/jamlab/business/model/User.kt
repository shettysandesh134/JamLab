package com.sandeshshetty.jamlab.business.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


class User(
    val id: String,
    var name: String,
    var email: String,
    var email_verified_at: String,
    var type: String,
    var status: String,
    var accessToken: String
){

}