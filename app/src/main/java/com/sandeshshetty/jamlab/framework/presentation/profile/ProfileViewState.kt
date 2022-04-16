package com.sandeshshetty.jamlab.framework.presentation.profile

import com.sandeshshetty.jamlab.business.domain.model.user.User

data class ProfileViewState(
    val message: String? = null,
    val success: Boolean = false,
    val user: User? = null
) {
}