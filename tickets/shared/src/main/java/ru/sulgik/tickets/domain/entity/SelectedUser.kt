package ru.sulgik.tickets.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class SelectedUser(
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val phone: String,
)

