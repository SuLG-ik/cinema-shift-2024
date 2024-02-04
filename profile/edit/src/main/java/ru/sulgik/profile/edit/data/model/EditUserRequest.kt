package ru.sulgik.profile.edit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class EditUserRequest(
    val phone: String,
    val profile: Profile
) {

    @Serializable
    data class Profile(
        @SerialName("firstname")
        val firstName: String,
        @SerialName("lastname")
        val lastName: String,
        @SerialName("middlename")
        val middleName: String,
        val email: String,
        val city: String,
        val phone: String,
    )
}