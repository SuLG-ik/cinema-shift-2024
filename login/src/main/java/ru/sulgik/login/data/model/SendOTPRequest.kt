package ru.sulgik.login.data.model

import kotlinx.serialization.Serializable


@Serializable
data class SendOTPRequest(
    val phone: String,
)

@Serializable
data class SendOTPResponse(
    val retryDelay: Int,
)
