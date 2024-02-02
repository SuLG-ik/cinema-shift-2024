package ru.sulgik.core.validation.user

data class UserMiddleNameValidationResult(
    val value: String,
    val error: UserInputError?,
)