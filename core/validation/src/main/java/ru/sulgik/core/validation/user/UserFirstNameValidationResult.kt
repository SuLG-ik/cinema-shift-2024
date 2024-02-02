package ru.sulgik.core.validation.user

data class UserFirstNameValidationResult(
    val value: String,
    val error: UserInputError?,
)