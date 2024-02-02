package ru.sulgik.core.validation.user

data class UserLastNameValidationResult(
    val value: String,
    val isFull: Boolean,
    val error: UserInputError?,
)