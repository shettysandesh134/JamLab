package com.sandeshshetty.jamlab.framework.presentation.authenticate.register

data class RegisterRequest(
    var name: String,
    var email: String,
    var password: String,
    var password_confirmation: String,
    val type: String = "patient"
) {
}