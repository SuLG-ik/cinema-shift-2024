package ru.sulgik.core.validation.user

data class UserLastNameValidationResult(
    val value: String,
    val isFull: Boolean,
    val error: Error?,
) {
    sealed interface Error {
        data object IncorrectLength : Error
        data object DifferentLanguages : Error
        data object IncorrectInput : Error
    }
}