package ru.sulgik.profile.edit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailsResponse(
    val user: User,
) {

    @Serializable
    data class User(
        @SerialName("firstname")
        val firstName: String? = null,
        @SerialName("lastname")
        val lastName: String? = null,
        @SerialName("middlename")
        val middleName: String? = null,
        val email: String? = null,
        val city: String? = null,
        val phone: String,
    )

}