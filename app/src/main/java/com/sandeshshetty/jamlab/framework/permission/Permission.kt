package com.sandeshshetty.jamlab.framework.permission

import android.Manifest
import android.Manifest.permission.*

sealed class Permission(vararg val permissions: String) {

    //Mandatory feature for video Calling
    object MandatoryFeatureForVideoCalling: Permission(RECORD_AUDIO, CAMERA)

    object Location : Permission(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)

    companion object{
        fun from(permission: String) = when(permission) {
            RECORD_AUDIO, CAMERA -> MandatoryFeatureForVideoCalling
            ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION -> Location
            else -> throw IllegalArgumentException("Unknown permission: $permission")
        }
    }

}
