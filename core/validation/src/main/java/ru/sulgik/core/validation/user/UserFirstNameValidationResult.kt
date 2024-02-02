package ru.sulgik.core.validation.user

data class UserFirstNameValidationResult(
    val value: String,
    val isFull: Boolean,
    val error: UserInputError?,
)