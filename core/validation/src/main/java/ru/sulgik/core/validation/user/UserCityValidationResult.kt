package ru.sulgik.core.validation.user

data class UserCityValidationResult(
    val value: String,
    val error: UserInputError?,
)