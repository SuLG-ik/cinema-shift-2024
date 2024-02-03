package ru.sulgik.profile.edit.domain.entity

import ru.sulgik.core.validation.user.UserInputError

data class UserProfileInput(
    val firstName: EditableField,
    val lastName: EditableField,
    val middleName: EditableField,
    val city: EditableField,
    val email: EditableField,
    val phone: PhoneField,
) {
    data class EditableField(
        val value: String,
        val error: UserInputError?,
    )

    data class PhoneField(
        val value: String,
    )
}

