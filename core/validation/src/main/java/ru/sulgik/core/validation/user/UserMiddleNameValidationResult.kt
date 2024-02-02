package ru.sulgik.core.validation.user

data class UserMiddleNameValidationResult(
    val value: String,
    val error: Error?,
) {
    sealed interface Error {
        data object DifferentLanguages : Error
        data object IncorrectInput : Error
    }
}