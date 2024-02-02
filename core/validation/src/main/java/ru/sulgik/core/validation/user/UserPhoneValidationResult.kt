package ru.sulgik.core.validation.user

data class UserPhoneValidationResult(
    val value: String,
    val isFull: Boolean,
    val error: UserInputError?,
)