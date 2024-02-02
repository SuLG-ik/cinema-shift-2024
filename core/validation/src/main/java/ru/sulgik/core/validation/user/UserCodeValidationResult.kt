package ru.sulgik.core.validation.user

data class UserCodeValidationResult(
    val value: String,
    val error: UserInputError?,
)