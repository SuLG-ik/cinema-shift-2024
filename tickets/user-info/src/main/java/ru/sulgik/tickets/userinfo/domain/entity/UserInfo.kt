package ru.sulgik.tickets.userinfo.domain.entity

import ru.sulgik.core.validation.user.UserInputError

data class UserInfo(
    val firstName: FirstNameField = FirstNameField("", UserInputError.IncorrectLength),
    val lastName: LastNameField = LastNameField("", UserInputError.IncorrectLength),
    val middleName: MiddleNameField = MiddleNameField("", null),
    val phone: PhoneField = PhoneField("", true, UserInputError.IncorrectLength),
) {
    data class FirstNameField(
        val value: String,
        val error: UserInputError?,
    )

    data class LastNameField(
        val value: String,
        val error: UserInputError?,
    )

    data class MiddleNameField(
        val value: String,
        val error: UserInputError?,
    )

    data class PhoneField(
        val value: String,
        val isEditable: Boolean,
        val error: UserInputError?,
    )
}

