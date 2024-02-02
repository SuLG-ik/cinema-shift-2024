package ru.sulgik.core.validation.user

data class UserPhoneValidationResult(
    val value: String,
    val error: UserInputError?,
)