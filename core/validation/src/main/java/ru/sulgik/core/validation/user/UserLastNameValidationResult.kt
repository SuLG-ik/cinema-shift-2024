package ru.sulgik.core.validation.user

data class UserLastNameValidationResult(
    val value: String,
    val error: UserInputError?,
)