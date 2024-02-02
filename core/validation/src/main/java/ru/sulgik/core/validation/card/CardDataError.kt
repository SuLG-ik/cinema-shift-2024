package ru.sulgik.core.validation.card

sealed interface CardDataError {
    data object IncorrectLength : CardDataError
    data object IncorrectValue : CardDataError
}