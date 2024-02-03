package ru.sulgik.login.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val phone: String,
    val code: String,
)

@Serializable
data class SignInResponse(
    val token: String,
)