package ru.sulgik.core.validation.user

data class UserEmailValidationResult(
    val value: String,
    val error: UserInputError?,
)