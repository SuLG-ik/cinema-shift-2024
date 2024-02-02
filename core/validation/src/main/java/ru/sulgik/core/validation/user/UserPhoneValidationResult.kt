package ru.sulgik.core.validation.user

data class UserPhoneValidationResult(
    val value: String,
    val isFull: Boolean,
    val error: Error?,
) {
    sealed interface Error {
        data object IncorrectLength : Error
        data object IncorrectInput : Error
    }
}