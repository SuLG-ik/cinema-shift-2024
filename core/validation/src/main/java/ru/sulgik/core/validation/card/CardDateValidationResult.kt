package ru.sulgik.core.validation.card

data class CardDateValidationResult(
    val value: String,
    val isFull: Boolean,
    val error: Error?,
) {
    sealed interface Error {
        data object IncorrectLength : Error
        data object IncorrectDate : Error
    }
}