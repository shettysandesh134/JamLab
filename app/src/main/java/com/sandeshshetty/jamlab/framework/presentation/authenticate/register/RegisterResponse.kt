package com.sandeshshetty.jamlab.framework.presentation.authenticate.register

data class RegisterResponse(
    var access_token: String? = null,
    var message: String? = null
) {
}